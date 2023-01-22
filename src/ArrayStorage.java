/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
                size --;
            }
        }
    }

    void save(Resume r) {
        storage[size()] = r;
        size ++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i ++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size()-1];
                storage[size()-1] = null;
                size --;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[size()];
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                allResumes[i] = storage[i];
            }
        }
        return allResumes;
    }

    int size() {
        return size;
    }
}
