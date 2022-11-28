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
    return symbol(ParserSym.EOF);
%eofval}
// Declaracions

id		= [A-Za-z_][A-Za-z0-9_]*
integer  = [0-9][0-9]*
string  = [\"](([A-Za-z0-9_)])|({blank}))*[\"]


//simbolos operadores
bg      = \>                 //verificat
sm      = \<                //verificat
bg_eq   = >=               //verificat
sm_eq   = <=              //verificat
eq      = =              //verificat
neg     = \!=           //verificat

add          = \+
sub          = \-
mul          = \*
div          = \/
mod          = \%
asig         = <-
smcol        = ;
twodots      = :
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
r_main      = main
r_print     = print
r_input     = input
r_string    = string

/* Altres */
new_line = ([\n\r]|(\n\r))+
blank = [ \t\r]+

/* Comentaris */
r_comment = "//"([^\n])*

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

{add}                    { System.out.println("ADD: "+this.yytext());
                           return symbol(ParserSym.add);}
{sub}                    { System.out.println("SUB: "+this.yytext() ) ;
                            return symbol(ParserSym.sub);}
{mul}                    { System.out.println("MUL: "+this.yytext());
                            return symbol(ParserSym.mul);}
{div}                    { System.out.println("DIV: "+this.yytext());
                            return symbol(ParserSym.div);}
{mod}                    { System.out.println("MOD: "+this.yytext());
                            return symbol(ParserSym.mod);}
{lparen}                 { System.out.println("LPAREN: "+this.yytext());
                            return symbol(ParserSym.lparen);}
{rparen}                 { System.out.println("RPAREN: "+this.yytext());
                            return symbol(ParserSym.rparen);}
"true"                   { System.out.println("TRUE: "+this.yytext());
                            return symbol(ParserSym.bool);}
"false"                   { System.out.println("FALSE: "+this.yytext());
                            return symbol(ParserSym.bool);}
{bg}                     {System.out.println("BG: "+this.yytext());
                            return symbol(ParserSym.bg);}
{string}                     {System.out.println("string: "+this.yytext());
                            return symbol(ParserSym.string);}
{r_string}                     {System.out.println("r_string: "+this.yytext());
                            return symbol(ParserSym.r_string);}

{sm}                     {System.out.println("SM <: "+this.yytext());
                            return symbol(ParserSym.sm);}
{sm_eq}                  {System.out.println("SM_EQ <=: "+this.yytext());
                            return symbol(ParserSym.sm_eq);}
{eq}                     {System.out.println("EQ =: "+this.yytext());
                            return symbol(ParserSym.eq);}
{neg}                    {System.out.println("NEG =: "+this.yytext());
                            return symbol(ParserSym.neg);}

{asig}                   {System.out.println("ASIG: "+this.yytext());
                            return symbol(ParserSym.asig);}
{bg_eq}                  { System.out.println("BIG_EQ: "+this.yytext());
                            return symbol(ParserSym.bg);}


{smcol}                  {System.out.println("PUNT I COMA: "+this.yytext());
                            return symbol(ParserSym.smcol);}

{twodots}                  {System.out.println("DOS PUNTS: "+this.yytext());
                            return symbol(ParserSym.twodots);}

{com}                    { System.out.println("COMA: "+this.yytext());
                            return symbol(ParserSym.com);}
{brcl}                   { System.out.println("BRCL }: "+this.yytext());
                            return symbol(ParserSym.brcl);}
{brop}                   {System.out.println("BROP {: "+this.yytext());
                            return symbol(ParserSym.brop);}
{lop}                    {System.out.println("Obrir [: "+this.yytext());
                            return symbol(ParserSym.lop);}
{lclose}                 {System.out.println("Tancar ]: "+this.yytext());
                            return symbol(ParserSym.lclose);}
{lparen}                 {System.out.println("LPAREN: "+this.yytext());
                            return symbol(ParserSym.lparen);}
{rparen}                 {System.out.println("RPAREN: "+this.yytext());
                            return symbol(ParserSym.rparen);}

{r_if}                   {System.out.println("If: "+this.yytext());
                            return symbol(ParserSym.r_if);}
{r_while}                   {System.out.println("while: "+this.yytext());
                            return symbol(ParserSym.r_while);}
{r_break}                   {System.out.println("breack: "+this.yytext());
                            return symbol(ParserSym.r_break);}
{r_for}                   {System.out.println("for: "+this.yytext());
                            return symbol(ParserSym.r_for);}
{r_switch}                   {System.out.println("switch: "+this.yytext());
                            return symbol(ParserSym.r_switch);}
{r_else}                   {System.out.println("else: "+this.yytext());
                            return symbol(ParserSym.r_else);}
{r_const}                   {System.out.println("const: "+this.yytext());
                            return symbol(ParserSym.r_const);}
{r_or}                   {System.out.println("or: "+this.yytext());
                            return symbol(ParserSym.r_or);}
{r_bool}                   {System.out.println("bool: "+this.yytext());
                            return symbol(ParserSym.r_bool);}
{r_default}                   {System.out.println("default: "+this.yytext());
                            return symbol(ParserSym.r_default);}
{r_case}                   {System.out.println("case: "+this.yytext());
                            return symbol(ParserSym.r_case);}
{r_not}                   {System.out.println("not: "+this.yytext());
                            return symbol(ParserSym.r_not);}
{r_and}                   {System.out.println("and: "+this.yytext());
                            return symbol(ParserSym.r_and);}
{r_comment}                {System.out.println("comment: "+this.yytext());}


{r_procedure}            {System.out.println("PROCEDURE: "+this.yytext());
                            return symbol(ParserSym.r_procedure);}
{r_int}                  {System.out.println("keyword int: "+this.yytext());
                            return symbol(ParserSym.r_int);}
{r_function}             {System.out.println("FUNCTION: "+this.yytext());
                            return symbol(ParserSym.r_function);}
{r_return}               {System.out.println("Return!: "+this.yytext());
                            return symbol(ParserSym.r_return);}
{r_main}                  {System.out.println("Return!: "+this.yytext());
                            return symbol(ParserSym.r_main);}
{r_input}                     {System.out.println("input: "+this.yytext());
                            return symbol(ParserSym.r_input);}
{r_print}                {System.out.println("print: "+this.yytext());
                            return symbol(ParserSym.r_print);}
{id}                     {System.out.println("ID: "+this.yytext());
                            return symbol(ParserSym.id);}
{integer}                {System.out.println("INT: "+this.yytext());
                            return symbol(ParserSym.integer);}





{blank}                  {}
{new_line}               {}
//[^]                      { return symbol(ParserSym.error);  }

/****************************************************************************/