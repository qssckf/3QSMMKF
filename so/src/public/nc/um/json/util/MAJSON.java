package nc.um.json.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.json.JSONException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MAJSON
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -846459333219934681L;
/*     */   
/*     */   MAJSON() {}
/*     */   
/*     */   static double checkDouble(double d)
/*     */     throws JSONException
/*     */   {
/*  20 */     if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
/*  21 */       throw new JSONException("Forbidden numeric value: " + d);
/*     */     }
/*  23 */     return d;
/*     */   }
/*     */   
/*     */   static Boolean toBoolean(Object value) {
/*  27 */     if ((value instanceof Boolean))
/*  28 */       return (Boolean)value;
/*  29 */     if ((value instanceof String)) {
/*  30 */       String stringValue = (String)value;
/*  31 */       if ("true".equalsIgnoreCase(stringValue))
/*  32 */         return Boolean.valueOf(true);
/*  33 */       if ("false".equalsIgnoreCase(stringValue)) {
/*  34 */         return Boolean.valueOf(false);
/*     */       }
/*     */     }
/*  37 */     return null;
/*     */   }
/*     */   
/*     */   static Double toDouble(Object value) {
/*  41 */     if ((value instanceof Double))
/*  42 */       return (Double)value;
/*  43 */     if ((value instanceof Number))
/*  44 */       return Double.valueOf(((Number)value).doubleValue());
/*  45 */     if ((value instanceof String)) {
/*     */       try {
/*  47 */         return Double.valueOf((String)value);
/*     */       }
/*     */       catch (NumberFormatException ignored) {}
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */   
/*     */   static Integer toInteger(Object value) {
/*  55 */     if ((value instanceof Integer))
/*  56 */       return (Integer)value;
/*  57 */     if ((value instanceof Number))
/*  58 */       return Integer.valueOf(((Number)value).intValue());
/*  59 */     if ((value instanceof String)) {
/*     */       try {
/*  61 */         return Integer.valueOf((int)Double.parseDouble((String)value));
/*     */       }
/*     */       catch (NumberFormatException ignored) {}
/*     */     }
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   static Long toLong(Object value) {
/*  69 */     if ((value instanceof Long))
/*  70 */       return (Long)value;
/*  71 */     if ((value instanceof Number))
/*  72 */       return Long.valueOf(((Number)value).longValue());
/*  73 */     if ((value instanceof String)) {
/*     */       try {
/*  75 */         return Long.valueOf((long) Double.parseDouble((String)value));
/*     */       }
/*     */       catch (NumberFormatException ignored) {}
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */   
/*     */   static String toString(Object value) {
/*  83 */     if ((value instanceof String))
/*  84 */       return (String)value;
/*  85 */     if (value != null) {
/*  86 */       return String.valueOf(value);
/*     */     }
/*  88 */     return null;
/*     */   }
/*     */   
/*     */   public static JSONException typeMismatch(Object indexOrName, Object actual, String requiredType) throws JSONException
/*     */   {
/*  93 */     if (actual == null) {
/*  94 */       throw new JSONException("Value at " + indexOrName + " is null.");
/*     */     }
/*  96 */     throw new JSONException("Value " + actual + " at " + indexOrName + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static JSONException typeMismatch(Object actual, String requiredType)
/*     */     throws JSONException
/*     */   {
/* 104 */     if (actual == null) {
/* 105 */       throw new JSONException("Value is null.");
/*     */     }
/* 107 */     throw new JSONException("Value " + actual + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
/*     */   }
/*     */ }

/* Location:           D:\CODE\xxhq-home\xxhq-home\external\lib\web_mobrt.jar
 * Qualified Name:     com.yonyou.uap.um.json.MAJSON
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */