package br.com.caelum.xstream;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

public class CategoriaTest {

	@Test(expected = CircularReferenceException.class)
	public void deveSerializarUmCiclo() {

		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);

		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("categoria", Categoria.class);
		xstream.toXML(esporte);

	}

}
