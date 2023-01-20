/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                System.out.println(storage[i]);
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        for (Resume resume : storage) {
            if (resume != null && resume.uuid.equals(uuid)) {
                r = resume;
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                for (int j = i; j < storage.length - 1; j++) {
                    storage[j] = storage[j + 1];
                    storage[j + 1] = null;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                size++;
            }
        }
        Resume[] withoutNullArray = new Resume[size];
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                withoutNullArray[i] = storage[i];
            }
        }
        return withoutNullArray;
    }

    int size() {
        int counter = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                counter++;
            }
        }
        return counter;
    }
}
