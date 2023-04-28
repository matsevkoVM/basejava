package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;
import com.urise.webapp.utils.JSONParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private static SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(conn, r);
            insertSection(conn, r);
            return null;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.<Void>transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=?  WHERE uuid=?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistResumeException(r.getUuid());
                }
            }
            deleteContacts(r);
            insertContacts(conn, r);
            deleteSection(r);
            insertSection(conn, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                "   SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "       ON r.uuid = c.resume_uuid " +
                "LEFT JOIN sections s" +
                "       ON r.uuid = s.resume_uuid" +
                "    WHERE r.uuid =?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistResumeException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            do {
                putContact(rs, r);
                putSections(rs, r);
            }
            while (rs.next());
            return r;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistResumeException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                "   SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "       ON r.uuid = c.resume_uuid " +
                "LEFT JOIN sections s" +
                "       ON r.uuid = s.resume_uuid" +
                " ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            Map<String, Resume> map = new LinkedHashMap<>();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                Resume resume = map.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, rs.getString("full_name"));
                    map.put(uuid, resume);
                }
                putContact(rs, resume);
                putSections(rs, resume);
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> pair : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, pair.getKey().name());
                ps.setString(3, pair.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSection(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO sections (resume_uuid, section_type, section_value) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> pair : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, pair.getKey().name());
                Section section = pair.getValue();
                ps.setString(3, JSONParser.write(section, Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Resume r) {
        sqlHelper.<Void>execute("DELETE FROM contact WHERE resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void deleteSection(Resume r) {
        sqlHelper.<Void>execute("DELETE FROM sections WHERE resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void putContact(ResultSet rs, Resume r) throws SQLException {
        if (r != null) {
            String value = rs.getString("value");
            if (value != null) {
                r.addContact(ContactType.valueOf(rs.getString("type")), value);
            }
        }
    }

    private void putSections(ResultSet rs, Resume r) throws SQLException {
        String content = rs.getString("section_value");
        if (content != null) {
            r.addSection(SectionType.valueOf(rs.getString("section_type")), JSONParser.read(content, Section.class));
        }
    }
}
