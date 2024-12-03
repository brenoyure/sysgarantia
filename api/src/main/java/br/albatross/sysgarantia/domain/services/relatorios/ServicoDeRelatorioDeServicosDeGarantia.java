package br.albatross.sysgarantia.domain.services.relatorios;

import java.util.List;

import br.albatross.sysgarantia.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantia;

/**
 * 
 * Responsável por retornar os dados de relatório das solicitações de garantia.
 * 
 * @author breno.brito
 */
public interface ServicoDeRelatorioDeServicosDeGarantia {

    List<RelatorioServicoDeGarantia> getRelatorioDosServicosDoFornecedor(int fornecedorId);

}
