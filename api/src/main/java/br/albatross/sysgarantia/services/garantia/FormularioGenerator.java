package br.albatross.sysgarantia.services.garantia;

import static java.io.File.createTempFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import br.albatross.sysgarantia.models.Cliente;
import br.albatross.sysgarantia.models.SolicitacaoGarantia;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormularioGenerator {

	private static final int CURRENT_YEAR = LocalDate.now().getYear();
	private static final String TEMP_FILE_PREFIX = "Abertura de Chamado ";
	private static final String TEMP_FILE_SUFFIX = ".docx";
	private static final String SPACE = " ";

	private static final String FORMULARIO_GARANTIA_DOCX = "Formulario-Garantia.docx";	

	public File getFormulario(SolicitacaoGarantia solicitacao) {
		try (XWPFDocument doc = new XWPFDocument(new BufferedInputStream(FormularioGenerator.class.getClassLoader().getResourceAsStream(FORMULARIO_GARANTIA_DOCX)))) {

            String numeroDeSerie = solicitacao.getNumeroDeSerie();
            String descricaoDoProblema = solicitacao.getDescricaoProblema().getDescricaoDetalhada();

			var xwpfTable = doc.getTableArray(0);

			    Cliente cliente = solicitacao.getCliente();			

				xwpfTable.getRow(2).getCell(1).setText(numeroDeSerie);
				xwpfTable.getRow(3).getCell(1).setText(cliente.getNome() + " - " + cliente.getDescricao());
				xwpfTable.getRow(7).getCell(1).setText(cliente.getNumerosParaContato());

				xwpfTable.getRow(10).getCell(1).setText(cliente.getLogradouro());
				xwpfTable.getRow(11).getCell(1).setText(cliente.getNumero());
				xwpfTable.getRow(13).getCell(1).setText(cliente.getBairro());
				xwpfTable.getRow(14).getCell(1).setText(cliente.getCidade());
				xwpfTable.getRow(15).getCell(1).setText(cliente.getEstado());
				xwpfTable.getRow(17).getCell(1).setText(cliente.getNumerosParaContato());

				LocalTime inicioDoExpediente = cliente.getHorarioInicioDoExpediente();

				LocalTime inicioDoHorarioDeAlmoco = cliente.getInicioDoHorarioDeAlmoco();
				LocalTime fimDoHorarioDeAlmoco = cliente.getFimDoHorarioDeAlmoco();

				LocalTime fimDoExpediente = cliente.getHorarioFimDoExpediente();

				xwpfTable.getRow(18).getCell(1).setText(inicioDoExpediente + " às " + fimDoExpediente);

				if (solicitacao.getCliente().isPossuiHorarioDeAlmoco()) {
				    xwpfTable.getRow(19).getCell(1).setText(inicioDoHorarioDeAlmoco + " às " + fimDoHorarioDeAlmoco);
				}

				xwpfTable.getRow(20).getCell(1).setText(descricaoDoProblema);

			var formularioTempFile = createTempFile(new StringBuilder()
																.append(TEMP_FILE_PREFIX)
																.append(numeroDeSerie)
																.append(SPACE)
																.append(String.valueOf(CURRENT_YEAR))
																.append(SPACE)
																.toString(), TEMP_FILE_SUFFIX);

			try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(formularioTempFile))) {
				doc.write(fos);
			}

			return formularioTempFile;

		} catch (IOException e) { throw new RuntimeException(e); }

	}

}
