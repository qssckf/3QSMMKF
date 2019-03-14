package nc.um.json.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.json.JSONException;
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
/*     */ public class MAJSONTokener
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1681562144098137838L;
/*     */   private final String in;
/*     */   private int pos;
/*     */   
/*     */   public MAJSONTokener(String in)
/*     */   {
/*  29 */     if ((in != null) && (in.startsWith("?"))) {
/*  30 */       in = in.substring(1);
/*     */     }
/*  32 */     this.in = in;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object nextValue()
/*     */     throws JSONException
/*     */   {
/*  43 */     int c = nextCleanInternal();
/*  44 */     switch (c) {
/*     */     case -1: 
/*  46 */       throw syntaxError("End of input");
/*     */     
/*     */     case 123: 
/*  49 */       return readObject();
/*     */  
/*     */     
/*     */     case 34: 
/*     */     case 39: 
/*  56 */       return nextString((char)c);
/*     */     }
/*     */     
/*  59 */     this.pos -= 1;
/*  60 */     return readLiteral();
/*     */   }
/*     */   
/*     */   private int nextCleanInternal() throws JSONException
/*     */   {
/*  65 */     while (this.pos < this.in.length()) {
/*  66 */       int c = this.in.charAt(this.pos++);
/*  67 */       switch (c)
/*     */       {
/*     */       case 9: 
/*     */       case 10: 
/*     */       case 13: 
/*     */       case 32: 
/*     */         break;
/*     */       case 47: 
/*  75 */         if (this.pos == this.in.length()) {
/*  76 */           return c;
/*     */         }
/*     */         
/*  79 */         char peek = this.in.charAt(this.pos);
/*  80 */         switch (peek)
/*     */         {
/*     */         case '*': 
/*  83 */           this.pos += 1;
/*  84 */           int commentEnd = this.in.indexOf("*/", this.pos);
/*  85 */           if (commentEnd == -1) {
/*  86 */             throw syntaxError("Unterminated comment");
/*     */           }
/*  88 */           this.pos = (commentEnd + 2);
/*  89 */           break;
/*     */         
/*     */ 
/*     */         case '/': 
/*  93 */           this.pos += 1;
/*  94 */           skipToEndOfLine();
/*  95 */           break;
/*     */         
/*     */         default: 
/*  98 */           return c;
/*     */         }
/*     */         
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         break;
/*     */       case 35: 
/* 107 */         skipToEndOfLine();
/* 108 */         break;
/*     */       
/*     */       default: 
/* 111 */         return c;
/*     */       }
/*     */       
/*     */     }
/* 115 */     return -1;
/*     */   }
/*     */   
/*     */   private void skipToEndOfLine()
/*     */   {
/* 124 */     for (; 
/*     */         
/*     */ 
/*     */ 
/* 124 */         this.pos < this.in.length(); this.pos += 1) {
/* 125 */       char c = this.in.charAt(this.pos);
/* 126 */       if ((c == '\r') || (c == '\n')) {
/* 127 */         this.pos += 1;
/* 128 */         break;
/*     */       }
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
/*     */ 
/*     */ 
/*     */   public String nextString(char quote)
/*     */     throws JSONException
/*     */   {
/* 149 */     StringBuilder builder = null;
/*     */     
/*     */ 
/* 152 */     int start = this.pos;
/*     */     
/* 154 */     while (this.pos < this.in.length()) {
/* 155 */       int c = this.in.charAt(this.pos++);
/* 156 */       if (c == quote) {
/* 157 */         if (builder == null)
/*     */         {
/* 159 */           return new String(this.in.substring(start, this.pos - 1));
/*     */         }
/* 161 */         builder.append(this.in, start, this.pos - 1);
/* 162 */         return builder.toString();
/*     */       }
/*     */       
/*     */ 
/* 166 */       if (c == 92) {
/* 167 */         if (this.pos == this.in.length()) {
/* 168 */           throw syntaxError("Unterminated escape sequence");
/*     */         }
/* 170 */         if (builder == null) {
/* 171 */           builder = new StringBuilder();
/*     */         }
/* 173 */         builder.append(this.in, start, this.pos - 1);
/* 174 */         builder.append(readEscapeCharacter());
/* 175 */         start = this.pos;
/*     */       }
/*     */     }
/*     */     
/* 179 */     throw syntaxError("Unterminated string");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private char readEscapeCharacter()
/*     */     throws JSONException
/*     */   {
/* 192 */     char escaped = this.in.charAt(this.pos++);
/* 193 */     switch (escaped) {
/*     */     case 'u': 
/* 195 */       if (this.pos + 4 > this.in.length()) {
/* 196 */         throw syntaxError("Unterminated escape sequence");
/*     */       }
/* 198 */       String hex = this.in.substring(this.pos, this.pos + 4);
/* 199 */       this.pos += 4;
/* 200 */       return (char)Integer.parseInt(hex, 16);
/*     */     
/*     */     case 't': 
/* 203 */       return '\t';
/*     */     
/*     */     case 'b': 
/* 206 */       return '\b';
/*     */     
/*     */     case 'n': 
/* 209 */       return '\n';
/*     */     
/*     */     case 'r': 
/* 212 */       return '\r';
/*     */     
/*     */     case 'f': 
/* 215 */       return '\f';
/*     */     }
/*     */     
/*     */     
/*     */ 
/*     */ 
/* 221 */     return escaped;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readLiteral()
/*     */     throws JSONException
/*     */   {
/* 231 */     String literal = nextToInternal("{}[]/\\:,=;# \t\f");
/*     */     
/* 233 */     if (literal.length() == 0)
/* 234 */       throw syntaxError("Expected literal value");
/* 235 */     if ("null".equalsIgnoreCase(literal))
/* 236 */       return MAJSONObject.NULL;
/* 237 */     if ("true".equalsIgnoreCase(literal))
/* 238 */       return Boolean.TRUE;
/* 239 */     if ("false".equalsIgnoreCase(literal)) {
/* 240 */       return Boolean.FALSE;
/*     */     }
/*     */     
/*     */ 
/* 244 */     if (literal.indexOf('.') == -1) {
/* 245 */       int base = 10;
/* 246 */       String number = literal;
/* 247 */       if ((number.startsWith("0x")) || (number.startsWith("0X"))) {
/* 248 */         number = number.substring(2);
/* 249 */         base = 16;
/* 250 */       } else if ((number.startsWith("0")) && (number.length() > 1)) {
/* 251 */         number = number.substring(1);
/* 252 */         base = 8;
/*     */       }
/*     */       try {
/* 255 */         long longValue = Long.parseLong(number, base);
/* 256 */         if ((longValue <= 2147483647L) && (longValue >= -2147483648L)) {
/* 257 */           return Integer.valueOf((int)longValue);
/*     */         }
/* 259 */         return Long.valueOf(longValue);
/*     */       }
/*     */       catch (NumberFormatException e) {}
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 272 */       return Double.valueOf(literal);
/*     */     }
/*     */     catch (NumberFormatException ignored) {}
/*     */     
/*     */ 
/* 277 */     return new String(literal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String nextToInternal(String excluded)
/*     */   {
/* 285 */     int start = this.pos;
/* 286 */     for (; this.pos < this.in.length(); this.pos += 1) {
/* 287 */       char c = this.in.charAt(this.pos);
/* 288 */       if ((c == '\r') || (c == '\n') || (excluded.indexOf(c) != -1)) {
/* 289 */         return this.in.substring(start, this.pos);
/*     */       }
/*     */     }
/* 292 */     return this.in.substring(start);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private MAJSONObject readObject()
/*     */     throws JSONException
/*     */   {
/* 300 */     MAJSONObject result = new MAJSONObject();
/*     */     
/*     */ 
/* 303 */     int first = nextCleanInternal();
/* 304 */     if (first == 125)
/* 305 */       return result;
/* 306 */     if (first != -1) {
/* 307 */       this.pos -= 1;
/*     */     }
/*     */     for (;;)
/*     */     {
/* 311 */       Object name = nextValue();
/* 312 */       if (!(name instanceof String)) {
/* 313 */         if (name == null) {
/* 314 */           throw syntaxError("Names cannot be null");
/*     */         }
/* 316 */         throw syntaxError("Names must be strings, but " + name + " is of type " + name.getClass().getName());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 325 */       int separator = nextCleanInternal();
/* 326 */       if ((separator != 58) && (separator != 61)) {
/* 327 */         throw syntaxError("Expected ':' after " + name);
/*     */       }
/* 329 */       if ((this.pos < this.in.length()) && (this.in.charAt(this.pos) == '>')) {
/* 330 */         this.pos += 1;
/*     */       }
/*     */       
/* 333 */       result.put((String)name, nextValue());
/*     */       
/* 335 */       switch (nextCleanInternal()) {
/*     */       case 125: 
/* 337 */         return result;
/*     */       }
/*     */       
/*     */     }
/*     */     
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
/*     */   public JSONException syntaxError(String message)
/*     */   {
/* 398 */     return new JSONException(message + this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 407 */     return " at character " + this.pos + " of " + this.in;
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
/*     */   public boolean more()
/*     */   {
/* 422 */     return this.pos < this.in.length();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char next()
/*     */   {
/* 431 */     return this.pos < this.in.length() ? this.in.charAt(this.pos++) : '\000';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public char next(char c)
/*     */     throws JSONException
/*     */   {
/* 439 */     char result = next();
/* 440 */     if (result != c) {
/* 441 */       throw syntaxError("Expected " + c + " but was " + result);
/*     */     }
/* 443 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char nextClean()
/*     */     throws JSONException
/*     */   {
/* 453 */     int nextCleanInt = nextCleanInternal();
/* 454 */     return nextCleanInt == -1 ? '\000' : (char)nextCleanInt;
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
/*     */   public String next(int length)
/*     */     throws JSONException
/*     */   {
/* 469 */     if (this.pos + length > this.in.length()) {
/* 470 */       throw syntaxError(length + " is out of bounds");
/*     */     }
/* 472 */     String result = this.in.substring(this.pos, this.pos + length);
/* 473 */     this.pos += length;
/* 474 */     return result;
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
/*     */ 
/*     */   public String nextTo(String excluded)
/*     */   {
/* 494 */     if (excluded == null) {
/* 495 */       throw new NullPointerException();
/*     */     }
/* 497 */     return nextToInternal(excluded).trim();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String nextTo(char excluded)
/*     */   {
/* 504 */     return nextToInternal(String.valueOf(excluded)).trim();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void skipPast(String thru)
/*     */   {
/* 513 */     int thruStart = this.in.indexOf(thru, this.pos);
/* 514 */     this.pos = (thruStart == -1 ? this.in.length() : thruStart + thru.length());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public char skipTo(char to)
/*     */   {
/* 523 */     int index = this.in.indexOf(to, this.pos);
/* 524 */     if (index != -1) {
/* 525 */       this.pos = index;
/* 526 */       return to;
/*     */     }
/* 528 */     return '\000';
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void back()
/*     */   {
/* 537 */     if (--this.pos == -1) {
/* 538 */       this.pos = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int dehexchar(char hex)
/*     */   {
/* 550 */     if ((hex >= '0') && (hex <= '9'))
/* 551 */       return hex - '0';
/* 552 */     if ((hex >= 'A') && (hex <= 'F'))
/* 553 */       return hex - 'A' + 10;
/* 554 */     if ((hex >= 'a') && (hex <= 'f')) {
/* 555 */       return hex - 'a' + 10;
/*     */     }
/* 557 */     return -1;
/*     */   }
/*     */ }

/* Location:           D:\CODE\xxhq-home\xxhq-home\external\lib\web_mobrt.jar
 * Qualified Name:     com.yonyou.uap.um.json.MAJSONTokener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */