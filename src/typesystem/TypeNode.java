package typesystem;
import java.util.ArrayList;
import java.util.List;

public class TypeNode
{
	private String typeName;
	private TypeNode parent;
	private List<TypeNode> children;
	
	public TypeNode(String name, TypeNode parent)
	{
		this.typeName = name;
		this.children = new ArrayList<TypeNode>();
		this.parent = parent;
	}
	public void addChildren(TypeNode node)
	{
		this.children.add(node);
	}
}
