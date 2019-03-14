package nc.um.json.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.json.JSONException;
/*     */ 
/*     */ public class MAJSONObject implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3542730705978959839L;
/*  15 */   private static final Double NEGATIVE_ZERO = Double.valueOf(-0.0D);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  32 */   public static final Object NULL = new Object()
/*     */   {
/*     */     public boolean equals(Object o) {
/*  35 */       return (o == this) || (o == null);
/*     */     }
/*     */     
/*     */     public int hashCode()
/*     */     {
/*  40 */       return super.hashCode();
/*     */     }
/*     */     
/*     */     public String toString()
/*     */     {
/*  45 */       return "null";
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */   private final Map<String, Object> nameValuePairs;
/*     */   
/*     */ 
/*     */   public MAJSONObject()
/*     */   {
/*  55 */     this.nameValuePairs = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject(Map<?, ?> copyFrom)
/*     */   {
/*  68 */     this();
/*  69 */     Map<?, ?> contentsTyped = copyFrom;
/*  70 */     for (Map.Entry<?, ?> entry : contentsTyped.entrySet())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  75 */       String key = (String)entry.getKey();
/*  76 */       if (key == null) {
/*  77 */         throw new NullPointerException();
/*     */       }
/*  79 */       this.nameValuePairs.put(key, entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject(MAJSONTokener readFrom)
/*     */     throws JSONException
/*     */   {
/*  97 */     Object object = readFrom.nextValue();
/*  98 */     if ((object instanceof MAJSONObject)) {
/*  99 */       this.nameValuePairs = ((MAJSONObject)object).nameValuePairs;
/*     */     } else {
/* 101 */       throw MAJSON.typeMismatch(object, "JSONObject");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject(String json)
/*     */     throws JSONException
/*     */   {
/* 114 */     this(new MAJSONTokener(json));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject(MAJSONObject copyFrom, String[] names)
/*     */     throws JSONException
/*     */   {
/* 123 */     this();
/* 124 */     for (String name : names) {
/* 125 */       Object value = copyFrom.opt(name);
/* 126 */       if (value != null) {
/* 127 */         this.nameValuePairs.put(name, value);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int length()
/*     */   {
/* 136 */     return this.nameValuePairs.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject put(String name, boolean value)
/*     */     throws JSONException
/*     */   {
/* 146 */     this.nameValuePairs.put(checkName(name), Boolean.valueOf(value));
/* 147 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject put(String name, double value)
/*     */     throws JSONException
/*     */   {
/* 159 */     this.nameValuePairs.put(checkName(name), Double.valueOf(MAJSON.checkDouble(value)));
/* 160 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject put(String name, int value)
/*     */     throws JSONException
/*     */   {
/* 170 */     this.nameValuePairs.put(checkName(name), Integer.valueOf(value));
/* 171 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject put(String name, long value)
/*     */     throws JSONException
/*     */   {
/* 181 */     this.nameValuePairs.put(checkName(name), Long.valueOf(value));
/* 182 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject put(String name, Object value)
/*     */     throws JSONException
/*     */   {
/* 197 */     if (value == null) {
/* 198 */       this.nameValuePairs.remove(name);
/* 199 */       return this;
/*     */     }
/* 201 */     if ((value instanceof Number))
/*     */     {
/* 203 */       MAJSON.checkDouble(((Number)value).doubleValue());
/*     */     }
/* 205 */     this.nameValuePairs.put(checkName(name), value);
/* 206 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public MAJSONObject putOpt(String name, Object value)
/*     */     throws JSONException
/*     */   {
/* 214 */     if ((name == null) || (value == null)) {
/* 215 */       return this;
/*     */     }
/* 217 */     return put(name, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 

/*     */   
/*     */   String checkName(String name) throws JSONException {
/* 256 */     if (name == null) {
/* 257 */       throw new JSONException("Names must be non-null");
/*     */     }
/* 259 */     return name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object remove(String name)
/*     */   {
/* 269 */     return this.nameValuePairs.remove(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isNull(String name)
/*     */   {
/* 277 */     Object value = this.nameValuePairs.get(name);
/* 278 */     return (value == null) || (value == NULL);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean has(String name)
/*     */   {
/* 286 */     return this.nameValuePairs.containsKey(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(String name)
/*     */     throws JSONException
/*     */   {
/* 295 */     Object result = this.nameValuePairs.get(name);
/* 296 */     if (result == null) {
/* 297 */       throw new JSONException("No value for " + name);
/*     */     }
/* 299 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object opt(String name)
/*     */   {
/* 307 */     return this.nameValuePairs.get(name);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getBoolean(String name)
/*     */     throws JSONException
/*     */   {
/* 318 */     Object object = get(name);
/* 319 */     Boolean result = MAJSON.toBoolean(object);
/* 320 */     if (result == null) {
/* 321 */       throw MAJSON.typeMismatch(name, object, "boolean");
/*     */     }
/* 323 */     return result.booleanValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean optBoolean(String name)
/*     */   {
/* 331 */     return optBoolean(name, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean optBoolean(String name, boolean fallback)
/*     */   {
/* 339 */     Object object = opt(name);
/* 340 */     Boolean result = MAJSON.toBoolean(object);
/* 341 */     return result != null ? result.booleanValue() : fallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDouble(String name)
/*     */     throws JSONException
/*     */   {
/* 352 */     Object object = get(name);
/* 353 */     Double result = MAJSON.toDouble(object);
/* 354 */     if (result == null) {
/* 355 */       throw MAJSON.typeMismatch(name, object, "double");
/*     */     }
/* 357 */     return result.doubleValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 

/*     */ 
/*     */ 
/*     */ 
/*     */   public double optDouble(String name, double fallback)
/*     */   {
/* 373 */     Object object = opt(name);
/* 374 */     Double result = MAJSON.toDouble(object);
/* 375 */     return result != null ? result.doubleValue() : fallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getInt(String name)
/*     */     throws JSONException
/*     */   {
/* 386 */     Object object = get(name);
/* 387 */     Integer result = MAJSON.toInteger(object);
/* 388 */     if (result == null) {
/* 389 */       throw MAJSON.typeMismatch(name, object, "int");
/*     */     }
/* 391 */     return result.intValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int optInt(String name)
/*     */   {
/* 399 */     return optInt(name, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int optInt(String name, int fallback)
/*     */   {
/* 407 */     Object object = opt(name);
/* 408 */     Integer result = MAJSON.toInteger(object);
/* 409 */     return result != null ? result.intValue() : fallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getLong(String name)
/*     */     throws JSONException
/*     */   {
/* 420 */     Object object = get(name);
/* 421 */     Long result = MAJSON.toLong(object);
/* 422 */     if (result == null) {
/* 423 */       throw MAJSON.typeMismatch(name, object, "long");
/*     */     }
/* 425 */     return result.longValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long optLong(String name)
/*     */   {
/* 433 */     return optLong(name, 0L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long optLong(String name, long fallback)
/*     */   {
/* 441 */     Object object = opt(name);
/* 442 */     Long result = MAJSON.toLong(object);
/* 443 */     return result != null ? result.longValue() : fallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getString(String name)
/*     */     throws JSONException
/*     */   {
/* 453 */     Object object = get(name);
/* 454 */     String result = MAJSON.toString(object);
/* 455 */     if (result == null) {
/* 456 */       throw MAJSON.typeMismatch(name, object, "String");
/*     */     }
/* 458 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String optString(String name)
/*     */   {
/* 466 */     return optString(name, "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String optString(String name, String fallback)
/*     */   {
/* 474 */     Object object = opt(name);
/* 475 */     String result = MAJSON.toString(object);
/* 476 */     return result != null ? result : fallback;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */ 
/*     */ 

/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject getJSONObject(String name)
/*     */     throws JSONException
/*     */   {
/* 512 */     Object object = get(name);
/* 513 */     if ((object instanceof MAJSONObject)) {
/* 514 */       return (MAJSONObject)object;
/*     */     }
/* 516 */     throw MAJSON.typeMismatch(name, object, "JSONObject");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONObject optJSONObject(String name)
/*     */   {
/* 525 */     Object object = opt(name);
/* 526 */     return (object instanceof MAJSONObject) ? (MAJSONObject)object : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 

/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<?> keys()
/*     */   {
/* 558 */     return this.nameValuePairs.keySet().iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 

/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*     */     try
/*     */     {
/* 576 */       MAJSONStringer stringer = new MAJSONStringer();
/* 577 */       writeTo(stringer);
/* 578 */       return stringer.toString();
/*     */     } catch (JSONException e) {}
/* 580 */     return "";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString(int indentSpaces)
/*     */     throws JSONException
/*     */   {
/* 600 */     MAJSONStringer stringer = new MAJSONStringer(indentSpaces);
/* 601 */     writeTo(stringer);
/* 602 */     return stringer.toString();
/*     */   }
/*     */   
/*     */   void writeTo(MAJSONStringer stringer) throws JSONException {
/* 606 */     stringer.object();
/* 607 */     for (Map.Entry<String, Object> entry : this.nameValuePairs.entrySet()) {
/* 608 */       stringer.key((String)entry.getKey()).value(entry.getValue());
/*     */     }
/* 610 */     stringer.endObject();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String numberToString(Number number)
/*     */     throws JSONException
/*     */   {
/* 620 */     if (number == null) {
/* 621 */       throw new JSONException("Number must be non-null");
/*     */     }
/*     */     
/* 624 */     double doubleValue = number.doubleValue();
/* 625 */     MAJSON.checkDouble(doubleValue);
/*     */     
/*     */ 
/* 628 */     if (number.equals(NEGATIVE_ZERO)) {
/* 629 */       return "-0";
/*     */     }
/*     */     
/* 632 */     long longValue = number.longValue();
/* 633 */     if (doubleValue == longValue) {
/* 634 */       return Long.toString(longValue);
/*     */     }
/*     */     
/* 637 */     return number.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String quote(String data)
/*     */   {
/* 648 */     if (data == null) {
/* 649 */       return "\"\"";
/*     */     }
/*     */     try {
/* 652 */       MAJSONStringer stringer = new MAJSONStringer();
/* 653 */       stringer.open(MAJSONStringer.Scope.NULL, "");
/* 654 */       stringer.value(data);
/* 655 */       stringer.close(MAJSONStringer.Scope.NULL, MAJSONStringer.Scope.NULL, "");
/* 656 */       return stringer.toString();
/*     */     } catch (JSONException e) {
/* 658 */       throw new AssertionError();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\CODE\xxhq-home\xxhq-home\external\lib\web_mobrt.jar
 * Qualified Name:     com.yonyou.uap.um.json.MAJSONObject
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */