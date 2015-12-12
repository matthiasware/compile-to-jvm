package typesystem;

public class Types
{
	private TypeTree typeTree;
	public Types()
	{
		this.typeTree = initTree();
	}
	private TypeTree initTree()
	{
		TypeNode root = new TypeNode("Object",null);
		TypeTree tt = new TypeTree(root);
		TypeNode intObject = new TypeNode("Integer", root);
		TypeNode methodObject = new TypeNode("Method",root);
		return null;
	}
}
