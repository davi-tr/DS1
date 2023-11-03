import requests

def cadastrarInstitutos():
    # Dados da requisição
    url = 'http://localhost:8083/instituto'
    data = {
        "nome": "Universidade Federal Fluminense",
        "acronimo": "UFF"
    }

    response = requests.post(url, json=data)

    # Verificar se a requisição foi bem-sucedida
    if response.status_code == 200 or response.status_code == 201:
        print("Dados inseridos com sucesso!")
    else:
        print(f"Erro ao fazer a requisição: {response.status_code}")


    url = 'http://localhost:8083/instituto'
    data = {
        "nome": "Universidade Federal do Rio de Janeiro",
        "acronimo": "UFRJ"
    }

    response = requests.post(url, json=data)

    if response.status_code == 200 or response.status_code == 201:
        print("Dados inseridos com sucesso!")
    else:
        print(f"Erro ao fazer a requisição: {response.status_code}")


    url = 'http://localhost:8083/instituto'
    data = {
        "nome": "Instituto Federal",
        "acronimo": "IFF"
    }

    response = requests.post(url, json=data)

    if response.status_code == 200 or response.status_code == 201:
        print("Dados inseridos com sucesso!")
    else:
        print(f"Erro ao fazer a requisição: {response.status_code}")


    url = 'http://localhost:8083/instituto'
    data = {
        "nome": "Instituto de Matematica",
        "acronimo": "IMAT"
    }

    response = requests.post(url, json=data)

    if response.status_code == 200 or response.status_code == 201:
        print("Dados inseridos com sucesso!")
    else:
        print(f"Erro ao fazer a requisição: {response.status_code}")


def cadastrarPesquisador():
    # Dados da requisição
    url = 'http://localhost:8083/pesquisador'
    id_instituto = 1  # Começar com o ID do instituto 1

    # Lista de IDs de Pesquisador
    lista_id_pesquisador = [
        "0023809873085852",
        "0024160866319507",
        "0028876341054325",
        "0047810385809553",
        "0053636364868790",
        "0066576690749759",
        "0082487176176434",
        "0110662125645595",
        "0112621452737067",
        "0161902355523060",
        "0194631586754988",
        "0235080730138338",
        "0263660448893625",
        "0329773854976808",
        "0348923590713594",
        "0485361810192703",
        "0491984479926888",
        "0549723858731158",
        "0559800226477492",
        "0600549075776976",
        "0604237405440586",
        "0658455060876989",
        "0659726776097432",
        "0676650998291996",
        "0692400140993944",
        "0743793296062293",
        "0770145420421898",
        "0781779929562675",
        "0814717344017544"
    ]

    # Realizar a requisição POST para cada ID de Pesquisador
    for id_pesquisador in lista_id_pesquisador:
        data = {
            "idPesquisador": id_pesquisador,
            "idinstituto": str(id_instituto)
        }

        response = requests.post(url, json=data)

        # Verificar se a requisição foi bem-sucedida
        if response.status_code == 200 or response.status_code == 201:
            print(f"Dados inseridos com sucesso para o pesquisador {id_pesquisador} e instituto {id_instituto}!")
        else:
            print(f"Erro ao fazer a requisição para o pesquisador {id_pesquisador} e instituto {id_instituto}: {response.status_code}")

        # Atualizar o ID do instituto para o próximo valor entre 1 e 4
        id_instituto = (id_instituto % 4) + 1


def cadastrarProducao():
# Dados da requisição
    url = 'http://localhost:8083/producao'

    # Lista de IDs de Pesquisador
    lista_id_pesquisador = [
        "0023809873085852",
        "0024160866319507",
        "0028876341054325",
        "0047810385809553",
        "0053636364868790",
        "0066576690749759",
        "0082487176176434",
        "0110662125645595",
        "0112621452737067",
        "0161902355523060",
        "0194631586754988",
        "0235080730138338",
        "0263660448893625",
        "0329773854976808",
        "0348923590713594",
        "0485361810192703",
        "0491984479926888",
        "0549723858731158",
        "0559800226477492",
        "0600549075776976",
        "0604237405440586",
        "0658455060876989",
        "0659726776097432",
        "0676650998291996",
        "0692400140993944",
        "0743793296062293",
        "0770145420421898",
        "0781779929562675",
        "0814717344017544"
    ]

    # Realizar a requisição POST para cada ID de Pesquisador
    for id_pesquisador in lista_id_pesquisador:
        data = {
            "idPesquisador": id_pesquisador
        }

        response = requests.post(url, json=data)

        # Verificar se a requisição foi bem-sucedida
        if response.status_code == 200 or response.status_code == 201:
            print(f"Dados inseridos com sucesso para o pesquisador {id_pesquisador}!")
        else:
            print(f"Erro ao fazer a requisição para o pesquisador {id_pesquisador}: {response.status_code}")


cadastrarInstitutos()
cadastrarPesquisador()
cadastrarProducao()
