//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 ".\exemploSem.y"
  import java.io.*;
  import java.util.ArrayList;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short DOUBLE=259;
public final static short VOID=260;
public final static short BOOL=261;
public final static short NUM=262;
public final static short STRING=263;
public final static short LITERAL=264;
public final static short AND=265;
public final static short MAIN=266;
public final static short IF=267;
public final static short FUNCT=268;
public final static short RETURN=269;
public final static short STRUCT=270;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    4,    7,    0,    5,    5,   10,    8,    9,    9,   12,
   12,   13,    3,    3,   16,   14,   17,   17,   18,   19,
   15,   15,    1,    1,    1,    6,   11,   20,   20,   21,
   21,    2,    2,    2,    2,    2,    2,    2,    2,    2,
   23,    2,    2,   22,   22,
};
final static short yylen[] = {                            2,
    0,    0,    5,    2,    0,    0,    5,    4,    5,    3,
    1,    2,    2,    0,    0,    5,    3,    1,    1,    0,
    5,    0,    1,    1,    1,    5,    3,    2,    0,    2,
    5,    3,    3,    3,    1,    3,    1,    3,    4,    3,
    0,    5,    2,    3,    1,
};
final static short yydefred[] = {                         1,
    0,    0,   23,   24,   25,   15,    0,    0,    0,    0,
    2,    0,   13,    0,    0,    0,    0,    4,    0,   19,
    0,   18,    6,    0,    3,   20,    0,   16,    0,    0,
    0,   17,    0,    7,    0,   21,    0,    0,    0,    0,
    0,    0,   12,    0,    0,   29,   26,    8,    0,   10,
    0,    9,    0,   35,    0,    0,    0,   27,    0,   28,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   30,
   40,    0,    0,   36,    0,    0,    0,    0,    0,    0,
    0,    0,   39,   42,    0,   31,    0,
};
final static short yydgoto[] = {                          1,
    6,   59,    7,    2,   11,   25,   17,   12,   34,   29,
   47,   39,   40,    8,   15,    9,   21,   22,   31,   51,
   60,   81,   72,
};
final static short yysindex[] = {                         0,
    0, -164,    0,    0,    0,    0, -262, -164,  -84, -164,
    0, -262,    0, -219, -209, -204, -197,    0,  -12,    0,
  -36,    0,    0, -184,    0,    0, -209,    0,   43,   49,
  -84,    0,  -16,    0,   50,    0, -164, -167,   65,   66,
  -10,  -10,    0, -164, -164,    0,    0,    0,  -10,    0,
  -40,    0,   71,    0,   74,  -38,  -38,    0,  -17,    0,
   75,  -38,  -11,  -24,  -38,  -38,  -38,  -38,  -38,    0,
    0,  -38,  -21,    0,   24,  -11,  -29,  -87,  -15,  -11,
  -20,  -39,    0,    0,  -38,    0,  -11,
};
final static short yyrindex[] = {                         0,
    0, -250,    0,    0,    0,    0, -143, -118, -138,    0,
    0, -143,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -138,    0,    0,    0,    0,    0,   -3,    0,    0,   81,
    0,    0,    0,   -3,    0,    0,    0,    0,    0,    0,
    0,    0,  -32,    0,    0,    0,    0,    0,    0,    0,
  -37,    0,   14,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -28,   16,   -5,   25,    0,    8,
    0,    0,    0,    0,    0,    0,   55,
};
final static short yygindex[] = {                         0,
   67,   36,   27,    0,  111,    0,    0,    0,    0,    0,
   30,   79,    0,    0,   94,    0,    0,   99,    0,    0,
   45,    0,    0,
};
final static int YYTABLESIZE=254;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   57,   57,   41,   69,   14,   10,   14,   27,   37,   14,
   37,   37,   34,   68,   34,   34,   74,   14,   68,   82,
   84,   68,   28,   85,   37,   68,   37,   68,   37,   37,
   34,   68,   34,   34,   13,   33,   66,   67,   33,   66,
   67,   70,   19,   66,   67,   66,   67,   20,   45,   66,
   67,   45,   23,   33,   43,   33,   38,   43,   37,   38,
   37,   69,   24,   42,   34,   32,   69,   32,   32,   69,
   49,   48,   43,   69,   38,   69,   16,   83,   52,   69,
   26,   30,   33,   32,   58,   32,   32,   33,   35,   43,
   41,   63,   64,    3,    4,   44,    5,   73,   44,   38,
   75,   76,   77,   78,   79,   44,   43,   80,   38,   45,
   61,   38,   46,   62,   69,   71,    5,   32,   22,   14,
   87,   11,   18,   50,   36,   32,   86,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   14,    0,    0,    0,    0,    0,    0,    0,   14,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   53,   53,   53,   41,
    0,   54,   54,   54,   41,    0,   55,   55,   56,   56,
   56,   41,   37,    0,    0,   65,   34,    0,    0,    0,
   65,    3,    4,   65,    5,    0,    0,   65,    0,   65,
    0,    0,    0,   65,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   40,   40,   91,  123,  268,   91,   44,   41,  260,
   43,   44,   41,   43,   43,   44,   41,  268,   43,   41,
   41,   43,   59,   44,   41,   43,   59,   43,   61,   62,
   59,   43,   61,   62,    8,   41,   61,   62,   44,   61,
   62,   59,  262,   61,   62,   61,   62,  257,   41,   61,
   62,   44,  257,   59,   41,   61,   41,   44,   91,   44,
   93,   91,  260,   37,   93,   41,   91,   43,   44,   91,
   44,   42,   59,   91,   59,   91,   10,   93,   49,   91,
   93,  266,   40,   59,  125,   61,   62,   93,   40,  257,
   41,   56,   57,  258,  259,   41,  261,   62,   44,   33,
   65,   66,   67,   68,   69,   41,   93,   72,   93,   44,
   40,   45,  123,   40,   91,   41,  260,   93,  257,  123,
   85,   41,   12,   45,   31,   27,   82,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  260,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  257,  257,  257,
   -1,  262,  262,  262,  262,   -1,  267,  267,  269,  269,
  269,  269,  265,   -1,   -1,  265,  265,   -1,   -1,   -1,
  265,  258,  259,  265,  261,   -1,   -1,  265,   -1,  265,
   -1,   -1,   -1,  265,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=270;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'","','",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","DOUBLE","VOID","BOOL",
"NUM","STRING","LITERAL","AND","MAIN","IF","FUNCT","RETURN","STRUCT",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"$$2 :",
"prog : $$1 dList fList $$2 main",
"fList : declFunc fList",
"fList :",
"$$3 :",
"declFunc : FUNCT type IDENT $$3 nDeclfuncT",
"nDeclfuncT : '(' ')' dList bloco",
"nDeclfuncT : '(' lstArgs ')' dList bloco",
"lstArgs : arg ',' lstArgs",
"lstArgs : arg",
"arg : type IDENT",
"dList : decl dList",
"dList :",
"$$4 :",
"decl : type $$4 TArray Lid ';'",
"Lid : Lid ',' id",
"Lid : id",
"id : IDENT",
"$$5 :",
"TArray : '[' NUM ']' $$5 TArray",
"TArray :",
"type : INT",
"type : DOUBLE",
"type : BOOL",
"main : VOID MAIN '(' ')' bloco",
"bloco : '{' listacmd '}'",
"listacmd : listacmd cmd",
"listacmd :",
"cmd : exp ';'",
"cmd : IF '(' exp ')' cmd",
"exp : exp '+' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : '(' exp ')'",
"exp : IDENT",
"exp : exp '=' exp",
"exp : exp '[' exp ']'",
"exp : IDENT '(' ')'",
"$$6 :",
"exp : IDENT '(' $$6 LExp ')'",
"exp : RETURN exp",
"LExp : LExp ',' exp",
"LExp : exp",
};

//#line 211 ".\exemploSem.y"

  private Yylex lexer;

  private TabSimb ts;
  private TabSimb tf;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  public static TS_entry Tp_VOID = new TS_entry("void", null,  ClasseID.TipoBase);
  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);
  
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private TS_entry currEscopo;
  private ClasseID currClass;

  private ArrayList<TS_entry> lstParams;

  private TS_entry currentType;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();
    tf = new TabSimb();
    lstParams = new ArrayList<TS_entry>();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_VOID);
    ts.insert(Tp_ARRAY);
    

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public void listarTF() { tf.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

//#line 436 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 25 ".\exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 2:
//#line 25 ".\exemploSem.y"
{ currClass = ClasseID.VarGlobal;
                                                         currEscopo = null; }
break;
case 6:
//#line 33 ".\exemploSem.y"
{ ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.NomeFuncao)); /*HERE*/
                              currEscopo = ts.pesquisa(val_peek(0).sval);
                            }
break;
case 12:
//#line 46 ".\exemploSem.y"
{  TS_entry nodo = ts.pesquisa(currEscopo.getId());
                    TS_entry newArg = new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.ArgFuncao, nodo);
                    ts.insert(newArg); /*here*/
                    nodo.addArgs((TS_entry)val_peek(1).obj);
                  }
break;
case 15:
//#line 59 ".\exemploSem.y"
{currentType = (TS_entry)val_peek(0).obj; }
break;
case 16:
//#line 60 ".\exemploSem.y"
{}
break;
case 19:
//#line 67 ".\exemploSem.y"
{ 
                TS_entry nodo = ts.pesquisa(val_peek(0).sval, currEscopo);
                if (nodo != null) 
                  yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");
                else if(currEscopo != null){ 
                  ts.insert(new TS_entry(val_peek(0).sval, currentType, ClasseID.VarLocal, currEscopo)); /*here*/
                }
                else{
                  ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass));  
                }
            }
break;
case 20:
//#line 81 ".\exemploSem.y"
{ currentType = new TS_entry("?", Tp_ARRAY, 
                                                   currClass, val_peek(1).ival, currentType); }
break;
case 23:
//#line 91 ".\exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 24:
//#line 92 ".\exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 25:
//#line 93 ".\exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 31:
//#line 107 ".\exemploSem.y"
{  if ( ((TS_entry)val_peek(2).obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(2).obj).getTipo());
                             }
break;
case 32:
//#line 113 ".\exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 33:
//#line 114 ".\exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 34:
//#line 115 ".\exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 35:
//#line 116 ".\exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 36:
//#line 117 ".\exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 37:
//#line 118 ".\exemploSem.y"
{ TS_entry nodo = null;
                    /*verifica se encontra o ident dentro do escopo*/
                    if(currEscopo != null){
                      nodo = ts.pesquisa(val_peek(0).sval, currEscopo);
                    }
                    /*se nao encontra procurra no global*/
                    if(nodo==null){
                      nodo = ts.pesquisa(val_peek(0).sval, null);
                    }

                    if (nodo == null) {
                      yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                      yyval.obj = Tp_ERRO;    
                    }           
                    else
                        yyval.obj = nodo.getTipo();
                  }
break;
case 38:
//#line 135 ".\exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 39:
//#line 136 ".\exemploSem.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
case 40:
//#line 144 ".\exemploSem.y"
{ 
                      TS_entry nodo = ts.pesquisa(val_peek(2).sval, currEscopo); /*here*/
                      if(nodo == null){
                        yyerror("funcao <" + val_peek(2).sval + "> nao declarada!");
                        yyval.obj = Tp_ERRO;
                      }
                      else{
                        if(nodo.getArgs().size() == 0){
                          yyval.obj = nodo.getTipo();
                        }else{
                          yyerror("funcao <" + val_peek(2).sval + "> espera 0 argumentos, recebeu: <" + nodo.getArgs().size() + ">");
                          yyval.obj = Tp_ERRO;
                        } 
                      }
                  }
break;
case 41:
//#line 159 ".\exemploSem.y"
{lstParams.clear();}
break;
case 42:
//#line 159 ".\exemploSem.y"
{
                          TS_entry nodo = ts.pesquisa(val_peek(4).sval);
                          Boolean erro = false;
                          
                          if(nodo == null){
                            yyerror("(sem) funct <" + val_peek(4).sval + "> nao declarada"); 
                            yyval.obj = Tp_ERRO;
                            erro = true;
                          }
                          
                          else if(nodo.getArgs().size() != lstParams.size()){
                            yyerror("funct <" + val_peek(4).sval + "> espera <"+ nodo.getArgs().size() +"> argumento(s), mas recebeu <" + lstParams.size() + "> argumento(s)!"); 
                            yyval.obj = Tp_ERRO;
                            erro = true;
                          }
                          
                          else{
                            for(int i=0; i < nodo.getArgs().size(); i++){
                              if(nodo.getArgs().get(i) != lstParams.get(i)){
                                String funcArgs = "";
                                String funcParam = "";
                                for(int j=0; j < nodo.getArgs().size(); j++){
                                  TS_entry arg = nodo.getArgs().get(j);
                                  funcArgs+= arg.getTipoStr() + " ";
                                }
                                for(int j=0; j < lstParams.size(); j++){
                                  TS_entry param = lstParams.get(j); 
                                  funcParam+= param.getTipoStr() + " ";
                                }
                                /*erro = True;*/
                                yyerror("funct <" + val_peek(4).sval + "> espera <" + nodo.getArgs().size() + "> parametro(s) < "+ funcArgs +">  mas recebeu <" + lstParams.size() + "> <"  + funcParam + "> !"); 
                                yyval.obj = Tp_ERRO;
                                erro = true;
                                break;
                              }
                            }
                          }
                          if(!erro)
                            yyval.obj = nodo.getTipo();
                        }
break;
case 43:
//#line 199 ".\exemploSem.y"
{
                      if(val_peek(0).obj != currEscopo.getTipo()){
                        yyerror("Erro funcao <" + currEscopo.getId() +"> tipo retornado <" + ((TS_entry)val_peek(0).obj).getTipoStr() +"> eh diferente do especificado na declaracao <"+currEscopo.getTipo().getId()+">");
                        yyval.obj = Tp_ERRO;
                      }
                  }
break;
case 44:
//#line 206 ".\exemploSem.y"
{lstParams.add((TS_entry)val_peek(0).obj);}
break;
case 45:
//#line 207 ".\exemploSem.y"
{lstParams.add((TS_entry)val_peek(0).obj);}
break;
//#line 790 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
