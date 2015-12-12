package symtable;

import java.util.HashMap;

public class Symboltable
{
	private HashMap<String,Entry> hm;
	private Symboltable enclosing;
	
	public Symboltable(Symboltable enclosing)
	{
		super();
		this.enclosing = enclosing;
	}
	public Symboltable()
	{
		hm = new HashMap<String,Entry>();
	}

	public void insert(Entry entry)
	{
		if(this.hm.containsKey(entry.getName()))
		{
			throw new IllegalArgumentException();
		}
		this.hm.put(entry.getName(), entry);
	}

	public Entry get(String name)
	{
		Entry returnEntry = this.hm.get(name);
		return returnEntry;
	}
}
