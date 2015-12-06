/* Generated By:JavaCC: Do not edit this line. InterpretadorMH.java */
package br.unb.cic.poo.MiniHaskell.parser;

import br.unb.cic.poo.MiniHaskell.*;
import java.util.*;

public class InterpretadorMH implements InterpretadorMHConstants {

  public static void main(String args []) throws ParseException
  {
    InterpretadorMH parser = new InterpretadorMH(System.in);
    while(true)
    {

                try{
                        Expressao exp = parser.expr();
                        Valor valor = exp.avaliar();

                        if(valor instanceof ValorInteiro){
                        ValorInteiro i = (ValorInteiro) valor;

                        System.out.println(i.getValor());
                }

                if(exp.avaliar() instanceof ValorBooleano){
                                ValorBooleano b = (ValorBooleano)valor;

                        System.out.println(b.getValor());
                }

                if(exp.avaliar() instanceof Lista)
                {
                                for(Lista lista = (Lista)valor; !lista.IsEmptyList(); lista = ((ListaValorada)lista).next()){
                                        ListaValorada l = (ListaValorada)lista;
                                        System.out.print(((ValorInteiro)l.valor()).getValor() + " ");
                                }
                                System.out.println("/");
                }

                }
                catch(RuntimeException ex)
                {
                        System.out.println("Erro!");
                        System.out.println(ex.getMessage());
                        //ex.printStackTrace();
                        continue;
                }
                catch(ParseException ex)
                {
                        System.out.println("Expressao invalida!");
                        //ex.printStackTrace();
                        parser.ReInit(System.in);
                        continue;
                }


        }
  }

  static final public Expressao getSoma() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoSoma(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getEquals() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoIgual(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getMaior() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoMaior(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getMenor() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoMenor(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getMaiorEq() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoMaiorOuIgual(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getMenorEq() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoMenorOuIgual(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getMult() throws ParseException {
        Expressao lhs;
        Expressao rhs;
    lhs = expr();
    rhs = expr();
         {if (true) return new ExpressaoMultiplicacao(lhs, rhs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getLet() throws ParseException {
        Expressao expAt;
        Expressao expCorpo;
        String id;
        Token idToken;
    idToken = jj_consume_token(STRING);
    jj_consume_token(EQUALS);
    expAt = expr();
    jj_consume_token(IN);
    expCorpo = expr();
         {if (true) return new ExpressaoLet(idToken.image, expAt, expCorpo);}
    throw new Error("Missing return statement in function");
  }

  static final public void getArgumentosDecFunc(ArrayList<String> argumentos) throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      t = jj_consume_token(STRING);
    argumentos.add(t.image);
      getArgumentosDecFunc(argumentos);
      break;
    case RPAR:
      jj_consume_token(RPAR);
                {if (true) return;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public DecFuncao getFunc() throws ParseException {
  Token nome;
  ArrayList<String> argumentos = new ArrayList<String>();
  Expressao exp;
    nome = jj_consume_token(STRING);
    jj_consume_token(LPAR);
    getArgumentosDecFunc(argumentos);
    jj_consume_token(LPAR);
    exp = expr();
    jj_consume_token(RPAR);
    {if (true) return(new DecFuncao(nome.image, argumentos, exp));}
    throw new Error("Missing return statement in function");
  }

  static final public void getParametrosAplFuncao(ArrayList<Expressao> exprs) throws ParseException {
  Expressao exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MENOR:
    case MAIOR:
    case IGUAL:
    case MENOR_EQ:
    case MAIOR_EQ:
    case MULT:
    case LET:
    case FUNC:
    case EVAL:
    case IF:
    case NEG:
    case LISTA:
    case INSERIR:
    case RECUPERA:
    case VALOR:
    case SIZE:
    case NUMBER:
    case STRING:
      exp = expr();
        exprs.add(exp);
      getParametrosAplFuncao(exprs);
      break;
    case RPAR:
      jj_consume_token(RPAR);
          {if (true) return;}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public AplicacaoDeFuncao getEval() throws ParseException {
  Token nome;
  ArrayList<Expressao> exprs = new ArrayList<Expressao>();
  AplicacaoDeFuncao func;
  Valor v;
  ValorInteiro inteiro;
    nome = jj_consume_token(STRING);
    jj_consume_token(LPAR);
    getParametrosAplFuncao(exprs);
    {if (true) return new AplicacaoDeFuncao(nome.image, exprs);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getInserir() throws ParseException {
        Expressao expValor;
        Expressao expLista;
    expValor = expr();
    jj_consume_token(IN);
    expLista = expr();
         {if (true) return new InserirNaLista(expValor, expLista);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getRecupera() throws ParseException {
        Expressao expIndex;
        Expressao expLista;
    expIndex = expr();
    jj_consume_token(FROM);
    expLista = expr();
         {if (true) return new RecuperaElemento(expIndex, expLista);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao getValor() throws ParseException {
        Expressao expLista;
    jj_consume_token(OF);
    expLista = expr();
         {if (true) return new ValorElementoLista(expLista);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao expr() throws ParseException {
  Token t;
  Token ref;
  Token func;
  ValorBooleano ret = new ValorBooleano(false);
  ExpressaoSoma s;
  Expressao if1, then1, else1;
  DecFuncao dec;
  Expressao exp;
  int lhs = 0;
  int rhs = 0;
  ValorInteiro res;
  Token negNumber;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_1;
        }
        exp = getSoma();
                 {if (true) return exp;}
      }
      break;
    case MULT:
      jj_consume_token(MULT);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_2;
        }
        exp = getMult();
                 {if (true) return exp;}
      }
      break;
    case NUMBER:
      t = jj_consume_token(NUMBER);
          {if (true) return new ValorInteiro(Integer.parseInt(t.image));}
      break;
    case LET:
      jj_consume_token(LET);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        exp = getLet();
                 {if (true) return exp;}
      }
      break;
    case STRING:
      ref = jj_consume_token(STRING);
          {if (true) return new ExpRef(ref.image);}
      break;
    case FUNC:
      jj_consume_token(FUNC);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case STRING:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_4;
        }
        dec = getFunc();
            AmbienteExecucao.getInstance().declaraFuncao(dec);
           {if (true) return ret = new ValorBooleano(true);}
      }
      break;
    case EVAL:
      jj_consume_token(EVAL);
      exp = getEval();
                {if (true) return exp;}
      break;
    case IGUAL:
      jj_consume_token(IGUAL);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_5;
        }
        exp = getEquals();
                 {if (true) return exp;}
      }
      break;
    case MAIOR:
      jj_consume_token(MAIOR);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_6;
        }
        exp = getMaior();
                 {if (true) return exp;}
      }
      break;
    case MENOR:
      jj_consume_token(MENOR);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[8] = jj_gen;
          break label_7;
        }
        exp = getMenor();
                 {if (true) return exp;}
      }
      break;
    case MAIOR_EQ:
      jj_consume_token(MAIOR_EQ);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[9] = jj_gen;
          break label_8;
        }
        exp = getMaiorEq();
                 {if (true) return exp;}
      }
      break;
    case MENOR_EQ:
      jj_consume_token(MENOR_EQ);
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[10] = jj_gen;
          break label_9;
        }
        exp = getMenorEq();
                 {if (true) return exp;}
      }
      break;
    case IF:
      jj_consume_token(IF);
      if1 = expr();
      jj_consume_token(THEN);
      then1 = expr();
      jj_consume_token(ELSE);
      else1 = expr();
         {if (true) return(new IfThenElse(if1, then1, else1));}
      break;
    case NEG:
      jj_consume_token(NEG);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_10;
        }
        exp = expr();
                 {if (true) return new ExpressaoMultiplicacao(new ValorInteiro(-1), exp);}
      }
      break;
    case LISTA:
      jj_consume_token(LISTA);
         {if (true) return new ListaVazia();}
      break;
    case INSERIR:
      jj_consume_token(INSERIR);
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[12] = jj_gen;
          break label_11;
        }
        exp = getInserir();
                 {if (true) return exp;}
      }
      break;
    case RECUPERA:
      jj_consume_token(RECUPERA);
      label_12:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case PLUS:
        case MENOR:
        case MAIOR:
        case IGUAL:
        case MENOR_EQ:
        case MAIOR_EQ:
        case MULT:
        case LET:
        case FUNC:
        case EVAL:
        case IF:
        case NEG:
        case LISTA:
        case INSERIR:
        case RECUPERA:
        case VALOR:
        case SIZE:
        case NUMBER:
        case STRING:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_12;
        }
        exp = getRecupera();
                 {if (true) return exp;}
      }
      break;
    case VALOR:
      jj_consume_token(VALOR);
      label_13:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OF:
          ;
          break;
        default:
          jj_la1[14] = jj_gen;
          break label_13;
        }
        exp = getValor();
                 {if (true) return exp;}
      }
      break;
    case SIZE:
      jj_consume_token(SIZE);
      label_14:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OF:
          ;
          break;
        default:
          jj_la1[15] = jj_gen;
          break label_14;
        }
        jj_consume_token(OF);
        exp = expr();
                {if (true) return new TamanhoLista(exp);}
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public InterpretadorMHTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80004000,0xebccdfe0,0xebcc9fe0,0xebcc9fe0,0x80000000,0x80000000,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0xebcc9fe0,0x10000000,0x10000000,0xebcc9fe0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public InterpretadorMH(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public InterpretadorMH(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new InterpretadorMHTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public InterpretadorMH(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new InterpretadorMHTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public InterpretadorMH(InterpretadorMHTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(InterpretadorMHTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[33];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 33; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
