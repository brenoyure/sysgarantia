<template>

<div style="text-align: center;">
  <p>
    Olá, sou Breno Yuri, estudante do curso de <i>Análise e Desenvolvimento de Sistemas</i> da faculdade <i>Unifanor Wyden</i>.
<br>
    Estudo programação, desde 2022, com os cursos do <i>Guanabara</i>, e logo em seguida, até então, pela plataforma <i>Alura</i>.
<br>
    Meu foco maior é na parte do backend, utilizando como linguagem principal o Java.
  </p>
  <p>
    Utilizo outras tecnologias para auxilar também, como a 
    ferramenta de containers <i>Docker</i>, simplificando bastante a parte de colocar o sistema em produção.
<br>
    Estou estudando também aos poucos as ferramentas <i>Terraform</i> e <i>Ansible</i> que automatizam a parte de provisionar e configurar um ambiente (VM) em nuvem,
    atualmente a da <i>AWS</i>. Estou engatinhando ainda, criando máquinas <i>EC2</i> e o banco de dados, utilizando o <i>RDS</i> (banco gerenciado), ao invés de 
    instalar o banco direto na VM ou utiliza-lo a partir do Docker.
  </p>
  <p>
    A ideia inicial deste projeto veio no meu trabalho anterior, quando acessamos o banco de dados do sistema de chamados, o <i>OTRS</i>, 
    para fazermos relatórios dos chamados para o PowerBI, e por curiosidade, começei a mapear as tabelas do banco em entidades (classes) da JPA do Java.
  </p>
  <p>
    Começei estudando as tabelas do banco, ao abrir um ticket de chamado, onde ficam todas as informações, como nome do serviço, responsável e etc.
    E como era armazenado a parte do inventário dos equipamentos, no <i>OTRS</i>, chamado de <i>CMDB</i>, antigo <i>BDGC</i>.
  </p>
  <p>
    A partir daí, começei a criar funções para retornar o número de série do equipamento baseado no seu identificador, listar os chamados utilizando filtros, como, 
    chamados abertos da data X à data Y, simulando exatamente relatórios.
  </p>
  <p>

  </p>
</div>

</template>