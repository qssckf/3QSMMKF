package nc.um.json.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.json.JSONException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MAJSONStringer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2763041722768311516L;
/*  18 */   final StringBuilder out = new StringBuilder();
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
/*     */   static enum Scope
/*     */   {
/*  31 */     EMPTY_ARRAY, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  37 */     NONEMPTY_ARRAY, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  43 */     EMPTY_OBJECT, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  49 */     DANGLING_KEY, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  55 */     NONEMPTY_OBJECT, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  61 */     NULL;
/*     */     
/*     */ 
/*     */     private Scope() {}
/*     */   }
/*     */   
/*     */ 
/*  68 */   private final List<Scope> stack = new ArrayList();
/*     */   
/*     */ 
/*     */   private final String indent;
/*     */   
/*     */ 
/*     */ 
/*     */   public MAJSONStringer()
/*     */   {
/*  77 */     this.indent = null;
/*     */   }
/*     */   
/*     */   MAJSONStringer(int indentSpaces) {
/*  81 */     char[] indentChars = new char[indentSpaces];
/*  82 */     Arrays.fill(indentChars, ' ');
/*  83 */     this.indent = new String(indentChars);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer array()
/*     */     throws JSONException
/*     */   {
/*  93 */     return open(Scope.EMPTY_ARRAY, "[");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer endArray()
/*     */     throws JSONException
/*     */   {
/* 102 */     return close(Scope.EMPTY_ARRAY, Scope.NONEMPTY_ARRAY, "]");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer object()
/*     */     throws JSONException
/*     */   {
/* 112 */     return open(Scope.EMPTY_OBJECT, "{");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer endObject()
/*     */     throws JSONException
/*     */   {
/* 121 */     return close(Scope.EMPTY_OBJECT, Scope.NONEMPTY_OBJECT, "}");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   MAJSONStringer open(Scope empty, String openBracket)
/*     */     throws JSONException
/*     */   {
/* 129 */     if ((this.stack.isEmpty()) && (this.out.length() > 0)) {
/* 130 */       throw new JSONException("Nesting problem: multiple top-level roots");
/*     */     }
/* 132 */     beforeValue();
/* 133 */     this.stack.add(empty);
/* 134 */     this.out.append(openBracket);
/* 135 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   MAJSONStringer close(Scope empty, Scope nonempty, String closeBracket)
/*     */     throws JSONException
/*     */   {
/* 143 */     Scope context = peek();
/* 144 */     if ((context != nonempty) && (context != empty)) {
/* 145 */       throw new JSONException("Nesting problem");
/*     */     }
/*     */     
/* 148 */     this.stack.remove(this.stack.size() - 1);
/* 149 */     if (context == nonempty) {
/* 150 */       newline();
/*     */     }
/* 152 */     this.out.append(closeBracket);
/* 153 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   private Scope peek()
/*     */     throws JSONException
/*     */   {
/* 160 */     if (this.stack.isEmpty()) {
/* 161 */       throw new JSONException("Nesting problem");
/*     */     }
/* 163 */     return (Scope)this.stack.get(this.stack.size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void replaceTop(Scope topOfStack)
/*     */   {
/* 170 */     this.stack.set(this.stack.size() - 1, topOfStack);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer value(Object value)
/*     */     throws JSONException
/*     */   {
/* 182 */     if (this.stack.isEmpty()) {
/* 183 */       throw new JSONException("Nesting problem");
/*     */     }
/*     */     

/* 190 */     if ((value instanceof MAJSONObject)) {
/* 191 */       ((MAJSONObject)value).writeTo(this);
/* 192 */       return this;
/*     */     }
/*     */     
/* 195 */     beforeValue();
/*     */     
/* 197 */     if ((value == null) || ((value instanceof Boolean)) || (value == MAJSONObject.NULL))
/*     */     {
/*     */ 
/* 200 */       this.out.append(value);
/*     */     }
/* 202 */     else if ((value instanceof Number)) {
/* 203 */       this.out.append(MAJSONObject.numberToString((Number)value));
/*     */     }
/*     */     else {
/* 206 */       string(value.toString());
/*     */     }
/*     */     
/* 209 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer value(boolean value)
/*     */     throws JSONException
/*     */   {
/* 218 */     if (this.stack.isEmpty()) {
/* 219 */       throw new JSONException("Nesting problem");
/*     */     }
/* 221 */     beforeValue();
/* 222 */     this.out.append(value);
/* 223 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer value(double value)
/*     */     throws JSONException
/*     */   {
/* 234 */     if (this.stack.isEmpty()) {
/* 235 */       throw new JSONException("Nesting problem");
/*     */     }
/* 237 */     beforeValue();
/* 238 */     this.out.append(MAJSONObject.numberToString(Double.valueOf(value)));
/* 239 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer value(long value)
/*     */     throws JSONException
/*     */   {
/* 248 */     if (this.stack.isEmpty()) {
/* 249 */       throw new JSONException("Nesting problem");
/*     */     }
/* 251 */     beforeValue();
/* 252 */     this.out.append(value);
/* 253 */     return this;
/*     */   }
/*     */   
/*     */   private void string(String value) {
/* 257 */     this.out.append("\"");
/* 258 */     int i = 0; for (int length = value.length(); i < length; i++) {
/* 259 */       char c = value.charAt(i);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 267 */       switch (c) {
/*     */       case '"': 
/*     */       case '/': 
/*     */       case '\\': 
/* 271 */         this.out.append('\\').append(c);
/* 272 */         break;
/*     */       
/*     */       case '\t': 
/* 275 */         this.out.append("\\t");
/* 276 */         break;
/*     */       
/*     */       case '\b': 
/* 279 */         this.out.append("\\b");
/* 280 */         break;
/*     */       
/*     */       case '\n': 
/* 283 */         this.out.append("\\n");
/* 284 */         break;
/*     */       
/*     */       case '\r': 
/* 287 */         this.out.append("\\r");
/* 288 */         break;
/*     */       
/*     */       case '\f': 
/* 291 */         this.out.append("\\f");
/* 292 */         break;
/*     */       
/*     */       default: 
/* 295 */         if (c <= '\037') {
/* 296 */           this.out.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
/*     */         } else {
/* 298 */           this.out.append(c);
/*     */         }
/*     */         break;
/*     */       }
/*     */       
/*     */     }
/* 304 */     this.out.append("\"");
/*     */   }
/*     */   
/*     */   private void newline() {
/* 308 */     if (this.indent == null) {
/* 309 */       return;
/*     */     }
/*     */     
/* 312 */     this.out.append("\n");
/* 313 */     for (int i = 0; i < this.stack.size(); i++) {
/* 314 */       this.out.append(this.indent);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MAJSONStringer key(String name)
/*     */     throws JSONException
/*     */   {
/* 325 */     if (name == null) {
/* 326 */       throw new JSONException("Names must be non-null");
/*     */     }
/* 328 */     beforeKey();
/* 329 */     string(name);
/* 330 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void beforeKey()
/*     */     throws JSONException
/*     */   {
/* 338 */     Scope context = peek();
/* 339 */     if (context == Scope.NONEMPTY_OBJECT) {
/* 340 */       this.out.append(',');
/* 341 */     } else if (context != Scope.EMPTY_OBJECT) {
/* 342 */       throw new JSONException("Nesting problem");
/*     */     }
/* 344 */     newline();
/* 345 */     replaceTop(Scope.DANGLING_KEY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void beforeValue()
/*     */     throws JSONException
/*     */   {
/* 354 */     if (this.stack.isEmpty()) {
/* 355 */       return;
/*     */     }
/*     */     
/* 358 */     Scope context = peek();
/* 359 */     if (context == Scope.EMPTY_ARRAY) {
/* 360 */       replaceTop(Scope.NONEMPTY_ARRAY);
/* 361 */       newline();
/* 362 */     } else if (context == Scope.NONEMPTY_ARRAY) {
/* 363 */       this.out.append(',');
/* 364 */       newline();
/* 365 */     } else if (context == Scope.DANGLING_KEY) {
/* 366 */       this.out.append(this.indent == null ? ":" : ": ");
/* 367 */       replaceTop(Scope.NONEMPTY_OBJECT);
/* 368 */     } else if (context != Scope.NULL) {
/* 369 */       throw new JSONException("Nesting problem");
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
/*     */   public String toString()
/*     */   {
/* 385 */     if (this.out == null) {
/* 386 */       return "";
/*     */     }
/* 388 */     return this.out.length() == 0 ? "" : this.out.toString();
/*     */   }
/*     */ }

/* Location:           D:\CODE\xxhq-home\xxhq-home\external\lib\web_mobrt.jar
 * Qualified Name:     com.yonyou.uap.um.json.MAJSONStringer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */