package person.dto;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class CommonDTO {

	@Override
	public String toString() {
		
		try {
			
			Map<String, String> map = BeanUtils.describe(this);
			
			map.entrySet().removeIf(entry -> entry.getValue() == null 
									|| entry.getValue().trim().isEmpty() 
									|| entry.getValue().equals("0") 
									|| entry.getValue().equals("0.0"));
			
			return map.toString();
			
		} catch (Exception e) {
			
			return null;
		}
	}
}
