import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;

/**
 * Directory tree node class.
 * @param <T> Type
 */
public class DirTreeNode<T> {

    private T data;
    private DirTreeNode<T> parent;
    private List<DirTreeNode<T>> children;

    /**
     * Constructs the Dir tree node.
     * @param data name of the node, either directory name or file name.
     */
    public DirTreeNode(T data){
        this.data = data;
        this.children = new LinkedList<>();
    }

    /**
     * Adds a child to the current node and adds this node as the parent node of the child
     * @param child the name of the child node
     * @return the child node
     */
    public DirTreeNode<T> addChild(T child){
        DirTreeNode<T> childNode = new DirTreeNode<>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    /**
     * Gets the parent of this node
     * @return this node's parent
     */
    public DirTreeNode<T> getParent(){
        return this.parent;
    }

    /**
     * Gets the list of children this node has
     * @return list of children
     */
    public List<DirTreeNode<T>> getChildren(){
        return this.children;
    }

    /**
     * Gets the data stored in this node, the name of the directory or file.
     * @return the data stored in this node
     */
    public T getData(){
        return this.data;
    }

}
