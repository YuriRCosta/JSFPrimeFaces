package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade>{

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvEntidade srvEntidade;
	
	public Entidade findUserLogado(String userLogado) throws Exception {
		
		return super.findUninqueByProperty(Entidade.class, userLogado,
				"ent_login", " and entity.ent_inativo is false ");
	}
	
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(name);
	}
	
	public void updateUltimoAcessoUser(String name) {
		srvEntidade.updateUltimoAcessoUser(name);
	}

	public boolean existeCpf(String cpf) throws Exception {
		return super.findListByQueryDinamica(" from Entidade where cpf = '" + cpf + "'").size() > 0;
	}

	public List<Entidade> pesquisarPorNome(String nome) throws Exception {
		return (List<Entidade>) getSession().createQuery("select e from Entidade e where e.ent_nomefantasia like '%" + nome + "%'").list();
	}

	
	
}
