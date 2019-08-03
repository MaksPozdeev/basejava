/**
 * Array based storage for Resumes
 *
 * @autor Maksim P.
 */
public class ArrayStorage {

    private int countResume = 0;
    private static final int SIZE_STORAGE = 10000;

    private Resume[] storage = new Resume[SIZE_STORAGE];

    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

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

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Resume (uuid: " + uuid + ") not found");
            return null;
        }
    }

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

    Resume[] getAll() {
        Resume[] resumes = new Resume[countResume];
        System.arraycopy(storage, 0, resumes, 0, countResume);
        return resumes;
    }

    int size() {
        return countResume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}