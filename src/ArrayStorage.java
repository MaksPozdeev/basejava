/**
 * Array based storage for Resumes
 *
 * @version 0.1
 * @autor Maksim P.
 * @lastChanges (2019.07.17) Maksim P.
 */
public class ArrayStorage {

    /**
     * Numbers of entries in the array
     */
    private int countResume = 0;

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    void save(Resume resume) {
        storage[countResume] = resume;
        countResume++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int position = -1;
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            for (int i = position; i < size(); i++) {
                storage[i] = storage[i + 1];
            }
            countResume--;
        } else {
            System.out.println("uuid not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
//        return new Resume[0];
        Resume[] resumes = new Resume[size()];
        System.arraycopy(storage, 0, resumes, 0, size());
        return resumes;
    }

    int size() {
        return countResume;
    }
}
