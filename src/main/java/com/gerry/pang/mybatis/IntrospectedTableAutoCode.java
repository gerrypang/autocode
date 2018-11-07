package com.gerry.pang.mybatis;

import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

public class IntrospectedTableAutoCode extends IntrospectedTableMyBatis3Impl {
	
	@Override
	protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }
		return super.createJavaClientGenerator();
	}

}
