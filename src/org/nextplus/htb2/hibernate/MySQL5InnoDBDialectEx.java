package org.nextplus.htb2.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MySQL5InnoDBDialectEx extends MySQL5InnoDBDialect {

	@SuppressWarnings("deprecation")
	public MySQL5InnoDBDialectEx() {
		super();
		registerFunction("dateadd", new SQLFunctionTemplate(Hibernate.DATE,
				"DATE_ADD(?1, INTERVAL ?2 ?3)"));
		
		registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING,
				"CONVERT(?1 USING ?2)"));
	}

}
