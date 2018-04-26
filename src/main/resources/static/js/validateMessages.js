$(".form-cadastro").validate({
	rules:{
		nome:{
			required: true
		},
		email:{
			required: true,
			email: true
		},
		confirmaSenha: {
			equalTo: "#password"
		},
		senha: {
			required: true
		}

	},
	messages:{
		nome:{
			required: "Preencha seu nome"
		},
		email:{
			required: "Preencha seu email",
			email: "Digite um email válido"
		},
		confirmaSenha:{
			equalTo: "Campo 'senha' e 'confirmar senha' precisam ser iguais"
		},
		senha:{
			required: "Coloque uma senha"
		}
	}
});

$("#form-login").validate({
	rules:{
		emailLogin:{
			required: true,
			email: true
		}
	},
	messages:{
		emailLogin:{
			required: "Preencha seu email",
			email: "Email inválido"
		}
	}
});
