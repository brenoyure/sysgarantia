##########################################################################################################################################
###                                                                                                                                    ###
###                                      V_10 - Criando a tabela de SolicitacaoGarantia e Email e EmailAnexo                           ###
###                                                                                                                                    ###
###  Com a nova regra de negócio, foi necessário implementar uma funcionalidade de criarmos a entidade para representar de fato uma    ###
###  solicitação de garantia, juntamente com seu e-mail e anexo de e-mail.                                                             ###
###                                                                                                                                    ###
###                                                                                                                                    ###
##########################################################################################################################################

CREATE TABLE IF NOT EXISTS solicitacao_garantia (
    id                       BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_de_serie          VARCHAR(50) NOT NULL,
    status                   ENUM('CRIADA', 'AGENDADA', 'ENVIADA') DEFAULT 'CRIADA',
    fk_cliente_id            INT NOT NULL,
    fk_fornecedor_id         INT NOT NULL,
    fk_descricao_problema_id INT NOT NULL,

    FOREIGN KEY (fk_cliente_id)            REFERENCES clientes(id),
    FOREIGN KEY (fk_fornecedor_id)         REFERENCES fornecedores(id),
    FOREIGN KEY (fk_descricao_problema_id) REFERENCES descricao_problema(id)

);

DESC solicitacao_garantia;

CREATE TABLE IF NOT EXISTS email (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    remetente VARCHAR(255) NOT NULL,
    destinatario TEXT NOT NULL,
    assunto VARCHAR(255) NOT NULL,
    corpo TEXT NOT NULL,
    copia_para TEXT DEFAULT NULL,
    copia_oculta TEXT DEFAULT NULL,
    fk_solicitacao_garantia_id BIGINT NOT NULL,

    FOREIGN KEY (fk_solicitacao_garantia_id) REFERENCES solicitacao_garantia(id)
);

DESC email;

CREATE TABLE IF NOT EXISTS email_anexos (
    id   BIGINT       PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    arquivo MEDIUMBLOB NOT NULL,
    fk_email_id BIGINT NOT NULL,

    FOREIGN KEY (fk_email_id) REFERENCES email(id)
);

DESC email_anexos;
