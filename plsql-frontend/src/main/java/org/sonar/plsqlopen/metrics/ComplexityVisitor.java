/*
 * Sonar PL/SQL Plugin (Community)
 * Copyright (C) 2015-2018 Felipe Zorzo
 * mailto:felipebzorzo AT gmail DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plsqlopen.metrics;

import org.sonar.plsqlopen.checks.PlSqlCheck;
import org.sonar.plugins.plsqlopen.api.PlSqlGrammar;
import org.sonar.plugins.plsqlopen.api.PlSqlKeyword;

import com.sonar.sslr.api.AstNode;

public class ComplexityVisitor extends PlSqlCheck {

    private int complexity;
    
    @Override
    public void init() {
        subscribeTo(
            PlSqlGrammar.CREATE_PROCEDURE,
            PlSqlGrammar.CREATE_FUNCTION,
            PlSqlGrammar.ANONYMOUS_BLOCK,
            
            PlSqlGrammar.PROCEDURE_DECLARATION,
            PlSqlGrammar.FUNCTION_DECLARATION,
            
            PlSqlGrammar.LOOP_STATEMENT,
            PlSqlGrammar.CONTINUE_STATEMENT,
            PlSqlGrammar.FOR_STATEMENT,
            PlSqlGrammar.EXIT_STATEMENT,
            PlSqlGrammar.IF_STATEMENT,
            PlSqlGrammar.RAISE_STATEMENT,
            PlSqlGrammar.RETURN_STATEMENT,
            PlSqlGrammar.WHILE_STATEMENT,
            
            // this includes WHEN in exception handlers, exit/continue statements and CASE expressions
            PlSqlKeyword.WHEN,
            PlSqlKeyword.ELSIF);
    }

    @Override
    public void visitFile(AstNode node) {
        complexity = 0;
    }

    @Override
    public void visitNode(AstNode node) {
        complexity++;
    }

    public int getComplexity() {
        return complexity;
    }
    
}
