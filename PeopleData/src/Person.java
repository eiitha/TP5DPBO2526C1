public class Person {
    private String id;
    private String name;
    private int birthYear;
    private String category;
    private String awardStatus;
    private String gender; // Atribut baru untuk JRadioButton

    public Person(String id, String name, int birthYear, String category, String awardStatus, String gender) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.category = category;
        this.awardStatus = awardStatus;
        this.gender = gender;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAwardStatus() { return awardStatus; }
    public void setAwardStatus(String awardStatus) { this.awardStatus = awardStatus; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}