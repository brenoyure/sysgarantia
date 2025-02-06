#!/bin/bash

# Exporta o realm e os usuários, em arquivos separados
kc.sh export --realm br.albatross.sysgarantia --users different_files --dir br.albatross.sysgarantia.realm.with.users --optimized

# Importa o Realm e os Usuários com a Opção de Sobrescrever
kc.sh import --dir ./br.albatross.sysgarantia.realm.with.users/ --override true
