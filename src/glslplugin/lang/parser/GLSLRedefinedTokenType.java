/*
 * Copyright 2010 Jean-Paul Balabanian and Yngve Devik Hammersland
 *
 *     This file is part of glsl4idea.
 *
 *     Glsl4idea is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as
 *     published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *     Glsl4idea is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with glsl4idea.  If not, see <http://www.gnu.org/licenses/>.
 */

package glslplugin.lang.parser;

import com.intellij.psi.tree.IElementType;
import glslplugin.lang.GLSLLanguage;

import java.util.List;

/**
 * @author Darkyen
 */
public final class GLSLRedefinedTokenType extends IElementType {

    public final List<PreprocessorToken> redefinedTo;
    private boolean marked = false;

    protected GLSLRedefinedTokenType(List<PreprocessorToken> redefinedTo) {
        super("GLSLRedefinedTokenType", GLSLLanguage.GLSL_LANGUAGE, false);
        this.redefinedTo = redefinedTo;
    }

    public boolean mark(){
        if(!marked){
            marked = true;
            return true;
        }else{
            return false;
        }
    }
}
