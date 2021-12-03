package person.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import person.annotation.Column;

public class PersonDTO {

	@Column("prsn_id")
	private Integer id;
	@Column("prsn_name")
	private String name;
	@Column("prsn_gender")
	private Character gender;
	@Column("prsn_age")
	private Short age;
	@Column("prsn_cpr")
	private Integer cpr;
	@Column("prsn_account_no")
	private Long accountNo;
	@Column("prsn_gpa")
	private Float gpa;
	@Column("prsn_salary")
	private Double salary;
	@Column("prsn_annual_income")
	private BigDecimal annualIncome;
	@Column("prsn_date_of_birth")
	private Date dateOfBirth;
	@Column("prsn_registration_date_time")
	private Timestamp registrationDateTime;
	@Column("prsn_sleep_time")
	private Time sleepTime;
	@Column("prsn_graduated")
	private Boolean graduated;
	@Column("prsn_certificates")
	private List<String> certificates;
	
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
	public List<String> getCertificates() {
		return certificates;
	}
	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", cpr=" + cpr
				+ ", accountNo=" + accountNo + ", gpa=" + gpa + ", salary=" + salary + ", annualIncome=" + annualIncome
				+ ", dateOfBirth=" + dateOfBirth + ", registrationDateTime=" + registrationDateTime + ", sleepTime="
				+ sleepTime + ", graduated=" + graduated + ", certificates=" + certificates + "]";
	}
}
