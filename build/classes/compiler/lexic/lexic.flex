/**
  Per poder compilar aquest fitxer s'ha d'haver instal·lat JFlex
 **/ 

/**
 * Assignatura 21780 - Compiladors
 * Estudis: Grau en Informàtica 
 * Itinerari: Intel·ligència Artificial i Computació
 *
 * Professor: Pere Palmer
 * Fitxer del léxic del nostre llenguatge
 */
package compiler.lexic;

import java.io.*;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

import compiler.sintactic.ParserSym;

%%
/** **
%standalone
 ** **/

/****
 Indicació de quin tipus d'analitzador sintàctic s'utilitzarà. En aquest cas
 es fa ús de Java CUP.
 ****/
%cup
/****
La línia anterior és una alternativa a la indicació element a element:

%implements java_cup.runtime.Scanner
%function next_token
%type java_cup.runtime.Symbol

****/

%public              // Per indicar que la classe és pública
%class Scanner       // El nom de la classe

%char
%line
%column

%eofval{

%eofval}
// Declaracions

id		= [A-Za-z_][A-Za-z0-9_]*
integer  = [0-9][0-9]*


//simbolos operadores
bg      = \>              //verificat
sm      = \<              //verificat
bg_eq   = >=           //verificat
sm_eq   = <=          //verificat
eq      = =              //verificat
neg     = \!=           //verificat

add          = \+
sub          = \-
mul          = \*
div          = \/
mod          = \%
asig         = <-
smcol        = ;
com          = ,
brop         = \{
brcl         = \}
lop          = \[
lclose       = \]
lparen       = \(
rparen       = \)

//Palabras reservadas
r_if        = if
r_else      = else
r_switch    = switch
r_case      = case
r_default   = default
r_break     = break
r_function  = func
r_procedure = proc
r_return    = return
r_while     = while
r_for       = for
r_const     = const
r_and       = and
r_or        = or
r_not       = not
r_int       = int
r_bool      = bool
r_true      = true
r_false     = false
r_main      = main

/* Altres */
new_line = ([\n\r]|(\n\r))+
blank = [ \t\r]+

/* Comentaris */
comment = "//"([^\n])*

// El següent codi es copiarà també, dins de la classe. És a dir, si es posa res
// ha de ser en el format adient: mètodes, atributs, etc. 
%{
    /***
       Mecanismes de gestió de símbols basat en ComplexSymbol. Tot i que en
       aquest cas potser no és del tot necessari.
     ***/
    /**
     Construcció d'un symbol sense atribut associat.
     **/
    private ComplexSymbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
    }
    
    /**
     Construcció d'un symbol amb un atribut associat.
     **/
    private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
    }

%}

/****************************************************************************/
%%

// Regles/accions
// És molt important l'ordre de les regles!!!

{add}                    { System.out.println("ADD: "+this.yytext()); }
{sub}                    { System.out.println("SUB: "+this.yytext() ) ;   }
{mul}                    { System.out.println("MUL: "+this.yytext());    }
{div}                    { System.out.println("DIV: "+this.yytext());    }
{mod}                    { System.out.println("MOD: "+this.yytext());     }
{lparen}                 { System.out.println("LPAREN: "+this.yytext());  }
{rparen}                 { System.out.println("RPAREN: "+this.yytext());  }

{bg}                     {System.out.println("BG: "+this.yytext());}

{sm}                     {System.out.println("SM <: "+this.yytext());}
{sm_eq}                  {System.out.println("SM_EQ <=: "+this.yytext());}
{eq}                     {System.out.println("EQ =: "+this.yytext());}
{neg}                    {System.out.println("NEG =: "+this.yytext());}

{asig}                   {System.out.println("ASIG: "+this.yytext()); }
{bg_eq}                  { System.out.println("BIG_EQ: "+this.yytext()); }


{smcol}                  {System.out.println("PUNT I COMA: "+this.yytext());}
{com}                    { System.out.println("COMA: "+this.yytext()); }
{brcl}                   { System.out.println("BRCL }: "+this.yytext()); }
{brop}                   {System.out.println("BROP {: "+this.yytext());}
{lop}                    {System.out.println("Obrir [: "+this.yytext());}
{lclose}                 {System.out.println("Tancar ]: "+this.yytext());}
{lparen}                 {System.out.println("LPAREN: "+this.yytext()); }
{rparen}                 {System.out.println("RPAREN: "+this.yytext()); }

{r_if}                   {System.out.println("If: "+this.yytext());}
{r_procedure}            {System.out.println("PROCEDURE: "+this.yytext()); }
{r_int}                  {System.out.println("keyword int: "+this.yytext());}
{r_function}             {System.out.println("FUNCTION: "+this.yytext()); }
{r_return}               {System.out.println("Return!: "+this.yytext());}
{r_main}                  {System.out.println("Return!: "+this.yytext());}

{id}                     {System.out.println("ID: "+this.yytext()); }
{integer}                {System.out.println("INT: "+this.yytext());}



{blank}                  {}
{new_line}               {}
//[^]                      { return symbol(ParserSym.error);  }

/****************************************************************************/