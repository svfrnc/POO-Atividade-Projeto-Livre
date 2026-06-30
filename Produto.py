class Produto:
    def __init__(self, id_produto, nome, preco, id_categoria, estoque_inicial=0):
        self.__id_produto = id_produto
        self.__nome = nome
        self.__preco = preco
        self.__id_categoria = id_categoria 
        self.__estoque = estoque_inicial

    def get_id_produto(self):
        return self.__id_produto

    def get_nome(self):
        return self.__nome

    def get_preco(self):
        return self.__preco

    def get_id_categoria(self):
        return self.__id_categoria

    def get_estoque(self):
        return self.__estoque

    def set_nome(self, novo_nome):
        if len(novo_nome) > 2:
            self.__nome = novo_nome
        else:
            raise ValueError("O nome do produto deve ter mais de 2 caracteres.")

    def set_preco(self, novo_preco):
        if novo_preco > 0:
            self.__preco = novo_preco
        else:
            raise ValueError("O preço deve ser maior que zero.")

    def adicionar_estoque(self, quantidade):
        if quantidade > 0:
            self.__estoque += quantidade

    def remover_estoque(self, quantidade):
        if quantidade > 0 and self.__estoque >= quantidade:
            self.__estoque -= quantidade
        else:
            raise ValueError("Estoque insuficiente para esta operação.")

    def to_dict(self):
        """Converte o objeto para um dicionário, facilitando o salvamento em arquivo JSON."""
        return {
            "id_produto": self.__id_produto,
            "nome": self.__nome,
            "preco": self.__preco,
            "id_categoria": self.__id_categoria,
            "estoque": self.__estoque
        }

    @classmethod
    def from_dict(cls, dados):
        """Cria uma instância de Produto a partir de um dicionário (lido do JSON)."""
        return cls(
            id_produto=dados["id_produto"],
            nome=dados["nome"],
            preco=dados["preco"],
            id_categoria=dados["id_categoria"],
            estoque_inicial=dados.get("estoque", 0)
        )

    def __str__(self):
        return f"[{self.__id_produto}] {self.__nome} - R$ {self.__preco:.2f} (Estoque: {self.__estoque})"