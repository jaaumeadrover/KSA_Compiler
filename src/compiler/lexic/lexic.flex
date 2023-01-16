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
integer  = {sub}?[0-9][0-9]*
str  = [\"](([A-Za-z0-9_)]) | ({blank}))*[\"]


//simbolos operadores
bg      = \>                 //verificat
sm      = \<                //verificat
bg_eq   = >=               //verificat
sm_eq   = <=              //verificat
eq      = =              //verificat
neq     = \!=           //verificat

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
r_array     = array

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
            ComplexSymbol s = new ComplexSymbol(ParserSym.terminalNames[type], type);
            s.left = yyline;
            s.right = yycolumn;
            return s;
        }
        /**
             Construcció d'un symbol amb un atribut associat.
        **/
        private ComplexSymbol symbol(int type, Object value) {
            ComplexSymbol s = new ComplexSymbol(ParserSym.terminalNames[type], type, value);
            s.left = yyline;
            s.right = yycolumn;
            return s;
}
%}

/****************************************************************************/
%%

// Regles/accions
// És molt important l'ordre de les regles!!!

{add}                    { return symbol(ParserSym.add);}
{sub}                    { return symbol(ParserSym.sub);}
{mul}                    { return symbol(ParserSym.mul);}
{div}                    { return symbol(ParserSym.div);}
{mod}                    { return symbol(ParserSym.mod);}
{lparen}                 { return symbol(ParserSym.lparen);}
{rparen}                 { return symbol(ParserSym.rparen);}
"true"                   { return symbol(ParserSym.bool,this.yytext());}
"false"                  { return symbol(ParserSym.bool,this.yytext());}
{bg}                     { return symbol(ParserSym.bg);}
{str}                 { return symbol(ParserSym.str,this.yytext());}
{r_string}               { return symbol(ParserSym.r_string);}

{sm}                     { return symbol(ParserSym.sm);}
{sm_eq}                  { return symbol(ParserSym.sm_eq);}
{eq}                     { return symbol(ParserSym.eq);}
{neq}                    { return symbol(ParserSym.neq);}

{asig}                   { return symbol(ParserSym.asig);}
{bg_eq}                  { return symbol(ParserSym.bg_eq);}


{smcol}                  { return symbol(ParserSym.smcol);}

{twodots}                { return symbol(ParserSym.twodots);}

{com}                    { return symbol(ParserSym.com);}
{brcl}                   { return symbol(ParserSym.brcl);}
{brop}                   { return symbol(ParserSym.brop);}
{lop}                    { return symbol(ParserSym.lop);}
{lclose}                 { return symbol(ParserSym.lclose);}
{lparen}                 { return symbol(ParserSym.lparen);}
{rparen}                 { return symbol(ParserSym.rparen);}

{r_if}                   { return symbol(ParserSym.r_if);}
{r_while}                { return symbol(ParserSym.r_while);}
{r_for}                  { return symbol(ParserSym.r_for);}
{r_switch}               { return symbol(ParserSym.r_switch);}
{r_else}                 { return symbol(ParserSym.r_else);}
{r_const}                { return symbol(ParserSym.r_const);}
{r_or}                   { return symbol(ParserSym.r_or);}
{r_bool}                 { return symbol(ParserSym.r_bool);}
{r_default}              { return symbol(ParserSym.r_default);}
{r_case}                 { return symbol(ParserSym.r_case);}
{r_not}                  { return symbol(ParserSym.r_not);}
{r_and}                  { return symbol(ParserSym.r_and);}
{r_array}                { return symbol(ParserSym.r_array,this.yytext());}
{r_comment}              {}

{r_procedure}            { return symbol(ParserSym.r_procedure);}
{r_int}                  { return symbol(ParserSym.r_int);}
{r_function}             { return symbol(ParserSym.r_function);}
{r_return}               { return symbol(ParserSym.r_return);}
{r_main}                 { return symbol(ParserSym.r_main);}
{r_input}                { return symbol(ParserSym.r_input);}
{r_print}                { return symbol(ParserSym.r_print);}
{id}                     { return symbol(ParserSym.id,this.yytext());}
{integer}                { return symbol(ParserSym.integer,this.yytext());}





{blank}                  {}
{new_line}               {}
//[^]                      { return symbol(ParserSym.error);  }

/****************************************************************************/