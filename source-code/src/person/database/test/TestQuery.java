package person.database.test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.beanutils.BeanUtils;
import person.database.Column;
import person.database.Query;
import person.dto.PersonDTO;

public class TestQuery extends Query {

	protected Object result;
	protected Entry<?, ?> entry;
	protected List<?> results;
	protected Map<?, ?> resultsMap;
	protected Map<Column, Object> record;
	protected List<Map<Column, Object>> records;
	protected Map<Object, Map<Column, Object>> recordsMap;
	protected String query = null;
	protected PersonDTO personDTO = new PersonDTO();
	protected List<PersonDTO> persons = new ArrayList<PersonDTO>();
	protected List<PersonDTO> expectedPersons = new ArrayList<PersonDTO>();
	protected Map<Integer, PersonDTO> personsMap = new LinkedHashMap<Integer, PersonDTO>();
	protected Map<Integer, PersonDTO> expectedPersonsMap = new LinkedHashMap<Integer, PersonDTO>();
	protected PersonDTO expectedPersonDTO = new PersonDTO();
	protected int id = 0;
	protected int secondId = 0;
	protected int count = 0;
	protected ExcelUtils excelUtils;
	protected List<Entry<Integer, Object>> parameters = new ArrayList<Entry<Integer, Object>>();
	
	public TestQuery(Connection connection) {
		super(connection);
	}
	
	public Date getSqlDate(Object object) {
		
		Date date = (Date) object;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = new Date(calendar.getTimeInMillis());
		
		return date;
	}
	
	public Timestamp getSqlTimestamp(Object object) {
		
		Timestamp timestamp = (Timestamp) object;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timestamp);
		calendar.set(Calendar.MILLISECOND, 0);
		timestamp = new Timestamp(calendar.getTimeInMillis());
		
		return timestamp;
	}
	
	public Time getSqlTime(Object object) {
		
		Time time = (Time) object;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MILLISECOND, 0);
		time = new Time(calendar.getTimeInMillis());
		
		return time;
	}
	
	public List<String> getSqlArray(Object object) {
		
		Array array = (Array) object;
		
		if(array != null) {
			try {
				return Arrays.asList((String[])array.getArray());
			} catch (SQLException e) {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public Object getSqlObject(Object object) {
		
		if(object == null) {
			return object;
		}else if(object instanceof Array) {
			return getSqlArray(object);
		}else if(object instanceof Date) {
			return getSqlDate(object);
		}else if(object instanceof Timestamp) {
			return getSqlTimestamp(object);
		}else if(object instanceof Time) {
			return getSqlTime(object);
		}else {
			return object;
		}
	}
	
	public Entry<Column, Object> entry(Column column, Object value){
		return new SimpleEntry<Column, Object>(column, value);
	}
	
	public Entry<Object, Map<Column, Object>> entry(Object key, Map<Column, Object> value){
		return new SimpleEntry<Object, Map<Column, Object>>(key, value);
	}
	
	public Map<Column, Object> map(Entry<Column, Object>... entries){
		
		Map<Column, Object> map = new LinkedHashMap<Column, Object>();
		
		for(Entry<Column, Object> entry : entries) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return map;
	}
	
	public Map<String, String> describe(Object object) throws Exception{
		
		Map<String, String> map = BeanUtils.describe(object);
		
		map.entrySet().removeIf(entry -> entry.getValue() == null 
				|| entry.getValue().trim().isEmpty() 
				|| entry.getValue().equals("0") 
				|| entry.getValue().equals("0.0"));
		
		return map;
	}
	
	public List<Map<String, String>> describe(List<?> objects) throws Exception{
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		for(Object object : objects) {
			
			list.add(describe(object));
		}
		
		return list;
	}
	
	public Map<Integer, Map<String, String>> describe(Map<Integer, ?> objects) throws Exception{
		
		Map<Integer, Map<String,String>> map = new LinkedHashMap<Integer, Map<String,String>>();
		
		for(Integer key : objects.keySet()) {
			
			map.put(key, describe(objects.get(key)));
		}
		
		return map;
	}
	
	public void isQueryEqual(String query, String expectedQuery) {
		
		System.out.println("query ==          " + query);
		System.out.println("expectedQuery ==  " + expectedQuery);
		
		if(query.equalsIgnoreCase(expectedQuery)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public List<Object> getQueryParameters(List<Entry<Integer, Object>> parameters) throws Exception{
		
		List<Object> values = new ArrayList<Object>();
		
		for(Entry<Integer, Object> parameter : parameters) {
			
			values.add(getSqlObject(parameter.getValue()));
		}
		
		return values;
	}
	
	public List<Object> getDatabaseValues(int id) throws Exception{
		
		List<Object> values = new ArrayList<Object>();
		
		try(PreparedStatement statement = connection.prepareStatement("select prsn_name, prsn_gender, prsn_age, prsn_cpr, prsn_account_no, prsn_gpa, prsn_salary, prsn_annual_income, prsn_date_of_birth, prsn_registration_date_time, prsn_sleep_time, prsn_graduated, prsn_certificates from public.person where prsn_id = " + id)){
			
			try(ResultSet result = statement.executeQuery()){
				
				if(result.next()){
					
					ResultSetMetaData resultSetMetaData = result.getMetaData();
					
					for(int i=1; i<=resultSetMetaData.getColumnCount(); i++) {
						
						values.add(getSqlObject(result.getObject(i)));
					}
				}
			}
		}

		return values;
	}
	
	public List<Object> getExpectedValues(Object[] objects){
		
		List<Object> expectedValues = Arrays.asList(objects);
		
		for(int i=0; i<expectedValues.size(); i++) {
			
			Object object = expectedValues.get(i);
			
			expectedValues.set(i, getSqlObject(object));
		}
		
		return expectedValues;
	}
	
	public void isParametersEquals(List<Entry<Integer, Object>> queryParams, Object...objects) throws Exception {
		
		List<Object> parameters = getQueryParameters(queryParams);
		List<Object> expectedParameters = getExpectedValues(objects);
		
		System.out.println("parameters ==         " + parameters);
		System.out.println("expectedParameters == " + expectedParameters);

		if(parameters.equals(expectedParameters)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isDatabaseValuesEquals(int id, Object...objects) throws Exception{
		
		List<Object> values = getDatabaseValues(id);
		List<Object> expectedValues = getExpectedValues(objects);
		
		System.out.println("values ==         " + values);
		System.out.println("expectedValues == " + expectedValues);
		
		if(values.equals(expectedValues)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isResultEqual(Object result, Object expectedResult) throws Exception{
		
		System.out.println("result ==         " + result);
		System.out.println("expectedResult == " + expectedResult);
		
		if(Arrays.asList(result).equals(Arrays.asList(expectedResult))) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isEntryEqual(Entry<?, ?> entry, Entry<?, ?> expectedEntry) throws Exception{
		
		System.out.println("entry ==         " + entry);
		System.out.println("expectedEntry == " + expectedEntry);
		
		if(entry.equals(expectedEntry)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isResultListEqual(List<?> results, Object... expectResults) throws Exception{
		
		List<Object> expectedResults = Arrays.asList(expectResults);
		
		System.out.println("results ==         " + results);
		System.out.println("expectedResults == " + expectedResults);
		
		if(Arrays.asList(results).equals(Arrays.asList(expectedResults))) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public Map<Object, Object> getExpectedMap(Entry<Object, Object>... entries) {
		
		Map<Object, Object> expectedMap = new LinkedHashMap<Object, Object>();
		
		for(Entry<Object, Object> entry : entries) {
			
			if(entry.getValue() != null) {
				
				entry.setValue(getSqlObject(entry.getValue()));
			}
			
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		
		return expectedMap;
	}

	public void isMapEqual(Map<?, ?> map, Entry<Object, Object>... entries) throws Exception{
		
		Map<Object, Object> expectedMap = getExpectedMap(entries);
		
		System.out.println("map == " + map);
		System.out.println("expectedMap == " + expectedMap);
		
		if(map.equals(expectedMap)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public Map<Column, Object> getDatabaseRecord(Map<Column, Object> record) {
		
		for(Entry<Column, Object> entry : record.entrySet()) {
			
			if(entry.getValue() != null) {
				
				entry.setValue(getSqlObject(entry.getValue()));
			}
		}
		
		return record;
	}
	
	public Map<Column, Object> getExpectedRecord(Entry<Column, Object>... entries) {
		
		Map<Column, Object> expectedRecord = new LinkedHashMap<Column, Object>();
		
		for(Entry<Column, Object> entry : entries) {
			
			if(entry.getValue() != null) {
				
				entry.setValue(getSqlObject(entry.getValue()));
			}
			
			expectedRecord.put(entry.getKey(), entry.getValue());
		}
		
		return expectedRecord;
	}
	
	public void isRecordEqual(Map<Column, Object> record, Entry<Column, Object>... entries) throws Exception{
		
		record = getDatabaseRecord(record);
		Map<Column, Object> expectedRecord = getExpectedRecord(entries);
		
		System.out.println("record ==         " + record);
		System.out.println("expectedRecord == " + expectedRecord);
		
		if(record.equals(expectedRecord)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isRecordListEqual(List<Map<Column, Object>> records, Map<Column, Object>... expectRecords) throws Exception{
		
		List<Map<Column, Object>> expectedRecords = Arrays.asList(expectRecords);
		
		System.out.println("records ==         " + records);
		System.out.println("expectedRecords == " + expectedRecords);
		
		if(records.equals(expectedRecords)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public Map<Object, Map<Column, Object>> getExpectedRecordMap(Entry<Object, Map<Column, Object>>... entries) {
		
		Map<Object, Map<Column, Object>> map = new LinkedHashMap<Object, Map<Column, Object>>();
		
		for(Entry<Object, Map<Column, Object>> entry : entries) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return map;
	}

	public void isRecordMapEqual(Map<Object, Map<Column, Object>> recordMap, Entry<Object, Map<Column, Object>>... expectRecordMap) throws Exception{
		
		Map<Object, Map<Column, Object>> expectedRecordMap = getExpectedRecordMap(expectRecordMap);
		
		System.out.println("recordMap ==         " + recordMap);
		System.out.println("expectedRecordMap == " + expectedRecordMap);
		
		if(recordMap.equals(expectedRecordMap)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isRecordEqual(Object object, Object expectedObject) throws Exception{
		
		Map<String, String> record = describe(object);
		Map<String, String> expectedRecord = describe(expectedObject);
		
		System.out.println("record ==         " + record);
		System.out.println("expectedRecord == " + expectedRecord);

		if(record.equals(expectedRecord)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public <T> void isRecordListEqual(List<?> recordList, List<?> expectRecords) throws Exception{
		
		List<Map<String, String>> records = describe(recordList);
		List<Map<String, String>> expectedRecords = describe(expectRecords);
		
		System.out.println("records ==         " + records);
		System.out.println("expectedRecords == " + expectedRecords);
		
		if(records.equals(expectedRecords)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void isRecordMapEqual(Map<Integer, ?> recordList, Map<Integer, ?> expectRecords) throws Exception{
		
		Map<Integer, Map<String, String>> records = describe(recordList);
		Map<Integer, Map<String, String>> expectedRecords = describe(expectRecords);
		
		System.out.println("records ==         " + records);
		System.out.println("expectedRecords == " + expectedRecords);
		
		if(records.equals(expectedRecords)) {
			excelUtils.addRecordValue("Yes");
		}else {
			excelUtils.addRecordValue("No");
		}
	}
	
	public void writeWorkbook() throws Exception{
		excelUtils.writeWorkbook();
	}
}
