package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PrecoConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {

		Double valor = (Double) object;
		NumberFormat formatter = getFormatador();
		String formatado = formatter.format(valor);

		writer.setValue(formatado);

	}

	private NumberFormat getFormatador() {
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		return formatter;
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String value = reader.getValue();
		try {
			return getFormatador().parse(value);
		} catch (ParseException e) {
			throw new ConversionException("Não consegui converter " + value, e);
		}
	}

}
