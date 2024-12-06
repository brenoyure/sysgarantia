package br.albatross.sysgarantia.externos;

import java.util.List;
import java.util.Optional;

import br.albatross.sysgarantia.externos.dto.Chamado;
import br.albatross.sysgarantia.externos.dto.Servico;

/**
 * Representa a integração com o Sistema de Chamados Externo, 
 * 
 * @author breno.brito
 */
public interface SistemaDeChamados {

    /**
     * Retorna uma lista de chamados, filtrada pelos IDs dos serviços.
     * Caso de uso de exemplo: Listar os Tickets abertos de algum fornecedor específico.
     * 
     * @author breno.brito
     */
    List<Chamado> findByService(List<Integer> servicesIds);
 
    /**
     * Retorna o número de série de algum equipamento, recebendo como 
     * parâmetro algum identificador que o sistema possua, por exemplo número de patrimônio.
     * 
     * @author breno.brito
     */
    Optional<String> findSerialNumberByIdentifier(String identificador);

    /**
     * 
     * Exibe a lista de Serviços disponíveis que os chamados podem possuir.
     * 
     * @author breno.brito
     * 
     */
    List<Servico> findAllTicketsServices();

}
