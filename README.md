# fugkyeehaw
```
BNF:
<program> -> <stmts>
<stmts> -> <stmt> | <stmt> <stmts>
<stmt> -> <decl_stmt> | <ass_stmt> | <loop_stmt> | <if_stmt> | 
<print_stmt>
<decl_stmt> -> num %identifier% | string %identifier%
<ass_stmt> -> %identifier% = <expr>
<loop_stmt> -> repeat <expr> ( <stmts> ) | repeat <bool_expr> ( <stmts> )
<if_stmt> -> if <bool_expr> ( <stmts> )
<print_stmt> -> show ( <expr> )
<expr> -> <val> | <val> + <expr>
<bool_expr> -> <expr> : <expr>
<val> -> %identifier% | %numeric_literal% | %string_literal%


EBNF:
<program> -> <stmts>
<stmts> -> <stmt> {<stmt>}
<stmt> -> <decl_stmt> | <ass_stmt> | <loop_stmt> | <if_stmt> | 
<print_stmt>
<decl_stmt> -> (num|string) %identifier%
<ass_stmt> -> %identifier% = <expr>
<loop_stmt> -> repeat <expr> [: <expr>] \( <stmts> \)
<if_stmt> -> if <bool_expr> \( <stmts> \)
<print_stmt> -> show \( <expr> \)
<expr> -> <val> { + <val>}
<bool_expr> -> <expr> : <expr>
<val> -> %identifier% | %numeric_literal% | %string_literal%

```
