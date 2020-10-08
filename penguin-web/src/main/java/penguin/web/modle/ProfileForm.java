package penguin.web.modle;

import javax.validation.constraints.Size;

public class ProfileForm {
	@Size(min = 2, max = 15)
    private String name;
    private Integer age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}