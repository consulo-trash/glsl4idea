/*
 *     Copyright 2010 Jean-Paul Balabanian and Yngve Devik Hammersland
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

package glslplugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.tree.IElementType;
import glslplugin.lang.elements.GLSLElementTypes;
import glslplugin.lang.elements.GLSLTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


// Todo: Blocks. Must have a PSI tree implemented...
public class GLSLFoldingBuilder implements FoldingBuilder {
    @NotNull
    public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode node, @NotNull Document document) {
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private void appendDescriptors(final ASTNode node, final List<FoldingDescriptor> descriptors) {
        IElementType type = node.getElementType();
        if (type == GLSLTokenTypes.COMMENT_BLOCK) {
            //todo: check if inside or outside method
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        } else if(type == GLSLElementTypes.COMPOUND_STATEMENT) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, descriptors);
            child = child.getTreeNext();
        }
    }

    public String getPlaceholderText(@NotNull ASTNode node) {
        if (node.getElementType() == GLSLTokenTypes.COMMENT_BLOCK) {
            return "/*...*/";
        }
        if (node.getElementType() == GLSLElementTypes.COMPOUND_STATEMENT) {
            return "{...}";
        }
        return null;
    }

    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }
}
