package br.com.caelum.xstream;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CompraDiferenteConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Compra.class);
	}

	@Override
	public void marshal(Object valor, HierarchicalStreamWriter writer, MarshallingContext context) {
		Compra compra = (Compra) valor;
		writer.addAttribute("estilo", "novo");
		
		writer.startNode("id");
		context.convertAnother(compra.getId());
		writer.endNode();
		
		writer.startNode("fornecedor");
		writer.setValue("johnathan.ciqueira@gmail.com");
		writer.endNode();
		
		writer.startNode("endereco");
		writer.startNode("linha1");
		writer.setValue("Rua Quintana, 753");
		writer.endNode();
		writer.startNode("linha2");
		writer.setValue("4 andar - Sao Paulo - SP");
		writer.endNode();
		writer.endNode();
		
		writer.startNode("produtos");
		context.convertAnother(compra.getProdutos());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String estilo = reader.getAttribute("estilo");
		reader.moveDown();
		String id = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		String fornecedor = reader.getValue();
		reader.moveUp();
		
		reader.moveDown();
		reader.moveDown();
		String linha1 = reader.getValue();
		reader.moveUp();
		reader.moveDown();
		String linha2 = reader.getValue();
		reader.moveUp();
		reader.moveUp();
		
		List<Produto> produtos = new ArrayList<Produto>();
		Compra compra = new Compra(Integer.parseInt(id), produtos );
		
		reader.moveDown();
		List<Produto> produtosLidos = (List<Produto>) context.convertAnother(compra, List.class);
		produtos.addAll(produtosLidos);
		reader.moveUp();
		
		return compra;
	}

}
