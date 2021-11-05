package person.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class PersonDTO {

	private Integer id;
	private String name;
	private Character gender;
	private Short age;
	private Integer cpr;
	private Long accountNo;
	private Float gpa;
	private Double salary;
	private BigDecimal annualIncome;
	private Date dateOfBirth;
	private Timestamp registrationDateTime;
	private Time sleepTime;
	private Boolean graduated;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public Integer getCpr() {
		return cpr;
	}
	public void setCpr(Integer cpr) {
		this.cpr = cpr;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public Float getGpa() {
		return gpa;
	}
	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Timestamp getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(Timestamp registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	public Time getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(Time sleepTime) {
		this.sleepTime = sleepTime;
	}
	public Boolean getGraduated() {
		return graduated;
	}
	public void setGraduated(Boolean graduated) {
		this.graduated = graduated;
	}
}
