package symtable;

public class ClassEntry extends Entry
{
	public ClassEntry(String name)
	{
		super(name);
	}
	private Symboltable declarations;
	
	public void setDeclarations(Symboltable declarations)
	{
		this.declarations = declarations;
	}
}
