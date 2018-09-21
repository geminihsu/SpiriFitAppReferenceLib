package spirit.fitness.scanner.until;

public class WeightPlateUtil {

	private static final String WEIGHT_PLATE_170Cartons = "900170";
	private static final String WEIGHT_PLATE_20_80 = "990080";
	private static final String WEIGHT_PLATE_200Cartons = "990200";
	private static final String WEIGHT_PLATE_90_150 = "990150";
	
	private static final String MA902 = "790274";
	private static final String MA906= "790675";
	
	private static final String MA900 = "790072";
	private static final String MA901 = "790172";
	private static final String MA903 = "990170";
	
	private static String MS7MODEL = "900MS7";
	private static String LP7MODEL = "900LP7";
	private static String FT7MODEL = "900FT7";
	private static String DR7MODEL = "900DR7";

	//For sales to check, they are not item
	private static String LIFTGATE_REQUIRED = "10030";
	private static String HOUR_REQUIRED = "10032";
	private static String INSIDE_DELIVERY_REQUIRED = "10034";
	private static String DELIVERY_REQUIRED_1 = "10049";
	private static String DELIVERY_REQUIRED_2 = "10050";
    private static String DELIVERY_REQUIRED_3 = "10040";

	
	private static String DROP_SHIP_FEE = "10048";
	
	public static boolean isWeightPlate(String item) {
		if (item.equals(WEIGHT_PLATE_170Cartons) || item.equals(WEIGHT_PLATE_20_80)
				|| item.equals(WEIGHT_PLATE_200Cartons) || item.equals(WEIGHT_PLATE_90_150))
			return true;

		return false;
	}
	
	
	public static boolean isCalfSupport(String item) {
		if (item.equals(MA902) || item.equals(MA906) || item.equals(MA900)|| item.equals(MA901)|| item.equals(MA903))
			return true;
		return false;
	}
	
	public static boolean isCheckBoxNoSNitem(String item) 
	{
		return (isCalfSupport(item) || isWeightPlate(item) || isShippingRequired(item));
	}
	
	
	public static String modelAppendWithPart(String modelNo) 
	{
		String result = "";
		if(modelNo.equals(MS7MODEL))
			result = " (900MS7-2, 900MS7-3, 90200 x3)";
		else if(modelNo.equals(LP7MODEL))
			result = " (990200 x1)";
		else if(modelNo.equals(FT7MODEL))
			result = " (900FT7-2, 990170 x1)";
		
		return result;	
	}
	
	public static boolean isModelParts(String modelNo) 
	{
		if(modelNo.equals(MS7MODEL) || modelNo.equals(LP7MODEL) || modelNo.equals(FT7MODEL)|| modelNo.equals(DR7MODEL))
			return true;
		return false;
	}
	
	
	public static boolean isShippingRequired(String required) 
	{
		if (required.equals(LIFTGATE_REQUIRED) || required.equals(HOUR_REQUIRED) || required.equals(INSIDE_DELIVERY_REQUIRED)|| required.equals(DELIVERY_REQUIRED_1)|| required.equals(DELIVERY_REQUIRED_2) ||required.equals(DELIVERY_REQUIRED_3) || required.equals(DROP_SHIP_FEE))
			return true;
		return false;
	}
}
