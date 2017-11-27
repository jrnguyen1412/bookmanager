package jp.suumai.bookmanager.component.support;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ConvertSupport {

	public <D> D toObject(Object src, Class<D> dest) {

		return new ModelMapper().map(src, dest);
	}
	
	public <D> D copyBean(Object src, Class<D> dest, String... ignoreProperties) throws InstantiationException, IllegalAccessException {

		D result = dest.newInstance();
		BeanUtils.copyProperties(src, result, ignoreProperties);
		
		return result;
	}
	
	public <D> D copyBean(Object src, Class<D> dest) throws InstantiationException, IllegalAccessException {

		D result = dest.newInstance();
		BeanUtils.copyProperties(src, result);
		
		return result;
	}

	public <D> List<D> toList(List<?> srcList, Class<D> dest) {

		Type type = new TypeToken<List<D>>() {}.getType();

		return new ModelMapper().map(srcList, type);
	}
	
	public <D> List<D> toBeanList(List<?> srcList, Class<D> dest, String... ignoreProperties) throws InstantiationException, IllegalAccessException {

		List<D> result = new ArrayList<>();
		
		for (Object src : srcList) {
			
			result.add(copyBean(src, dest, ignoreProperties));
		}
		
		return result;
	}
	
	public <D> List<D> toBeanList(List<?> srcList, Class<D> dest) throws InstantiationException, IllegalAccessException {

		List<D> result = new ArrayList<>();
		
		for (Object src : srcList) {
			
			result.add(copyBean(src, dest));
		}
		
		return result;
	}
	
	public <D> List<D> toLinkList(List<LinkedHashMap<String, String>> srcList, Class<D> dest) {

		List<D> result = new ArrayList<D>();
		
		for (LinkedHashMap<String, String> src : srcList) {
			
			result.add(toObject(src, dest));
		}

		return result;
	}
	
//    /**
//     * Copies properties from one object to another
//     * @param source
//     * @destination
//     * @return
//     */
//    public void copyNonNullProperties(Object source, Object destination){
//		BeanUtils.copyProperties(source, destination,
//				getNullPropertyNames(source));
//    }
//    /**
//     * Returns an array of null properties of an object
//     * @param source
//     * @return
//     */
//    private String[] getNullPropertyNames (Object source) {
//	    final BeanWrapper src = new BeanWrapperImpl(source);
//	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
// 
//	    @SuppressWarnings("rawtypes")
//		Set emptyNames = new HashSet();
//	    for(java.beans.PropertyDescriptor pd : pds) {
//	    	//check if value of this property is null then add it to the collection
//	        Object srcValue = src.getPropertyValue(pd.getName());
//	        if (srcValue == null) emptyNames.add(pd.getName());
//	    }
//	    String[] result = new String[emptyNames.size()];
//	    return (String[]) emptyNames.toArray(result);
//	}
}
