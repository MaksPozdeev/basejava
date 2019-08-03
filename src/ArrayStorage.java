/**
 * Array based storage for Resumes
 *
 * @autor Maksim P.
 */
public class ArrayStorage {

    /**
     * Numbers of entries in the array
     */
    private int countResume = 0;
    private static final int SIZE_STORAGE = 10000;

    private Resume[] storage = new Resume[SIZE_STORAGE];

    /**
     * clear all record. all -> null
     */
    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    /**
     * Save record.
     *
     * @param resume updated record.
     */
    void save(Resume resume) {
        if (countResume == SIZE_STORAGE) {
            System.out.println("Cannot be saved: the storage is full!");
            return;
        }
        if (resume != null) {
            if (findIndex(resume.getUuid()) != -1) {
                System.out.println("Resume " + resume.getUuid() + "already exists!");
                return;
            }
            storage[countResume] = resume;
            countResume++;
        }
    }

    /**
     * update record
     *
     * @param resume updated record.
     */
    void update(Resume resume) {
        if (resume != null) {
            int index = findIndex(resume.getUuid());
            if (index != -1) {
                storage[index] = resume;
            } else {
                System.out.println("Resume not found");
            }
        }
    }

    /**
     * Get record.
     *
     * @param uuid Search parameter.
     * @return found record.
     */
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume (uuid: " + uuid + ") not found");
            return null;
        }
    }

    /**
     * Delete record
     *
     * @param uuid Delete parameter.
     */
    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
        } else {
            System.out.println("Resume (uuid: " + uuid + ") not found");
        }
    }

    /**
     * Get all.
     *
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[countResume];
        System.arraycopy(storage, 0, resumes, 0, countResume);
        return resumes;
    }

    /**
     * Size.
     *
     * @return The number of entries in the array (Resume[] storage).
     **/
    int size() {
        return countResume;
    }

    /**
     * Search for an entry in an array (Resume[] storage).
     *
     * @param uuid Search parameter.
     * @return Array position number or -1 if record not found.
     */
    private int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}