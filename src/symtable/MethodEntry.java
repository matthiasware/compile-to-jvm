package symtable;

public class MethodEntry extends Entry
{
	public MethodEntry(String name)
	{
		super(name);
	}
	String returnType;
	Symboltable parameters;
	Symboltable locals;
}
