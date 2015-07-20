package br.com.devianto.anjo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.sql.SQLException;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.devianto.anjo.restmodel.models.Cliente;
import br.com.devianto.anjo.restmodel.models.DadosPagamento;
import br.com.devianto.anjo.restmodel.models.Endereco;
import br.com.devianto.anjo.restmodel.models.Estado;
import br.com.devianto.anjo.restmodel.models.FormaPagamento;
import br.com.devianto.anjo.restmodel.models.Imagem;
import br.com.devianto.anjo.restmodel.models.ItemPedido;
import br.com.devianto.anjo.restmodel.models.Loja;
import br.com.devianto.anjo.restmodel.models.Pedido;
import br.com.devianto.anjo.restmodel.models.Produto;
import br.com.devianto.anjo.restmodel.models.Secao;


public class DbHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "allinshopping.db";
	private static final Integer DATABASE_VERSION = 1;
//	private Dao<CEP, Long> cepDAO;
	private Dao<Endereco, Long> enderecoDAO;
	private Dao<Loja, Long> lojaDAO;
	private Dao<Cliente, Long> clienteDao;
	private Dao<Estado, Long> estadoDao;
//	private Dao<FaixaPreco, Long> faixaPrecoDao;
	private Dao<FormaPagamento, Long> formaPagamentoDao;
	private Dao<ItemPedido, Long> itemDao;
	private Dao<DadosPagamento, Long> pagtoDao;
	private Dao<Pedido, Long> pedidoDao;
	private Dao<Produto, Long> produtoDao;
	private Dao<Secao, Long> secaoDao;
	private Dao<Imagem, Long> imagemDao;
//	private Dao<Atributo, Long> atributoDao;

	public DbHelper(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private void createDatabase(final ConnectionSource connectionsource) {
		try {
			TableUtils.createTable(connectionsource, Estado.class);
			TableUtils.createTable(connectionsource, Cliente.class);
			TableUtils.createTable(connectionsource, Endereco.class);
			TableUtils.createTable(connectionsource, FormaPagamento.class);
			TableUtils.createTable(connectionsource, Pedido.class);
			TableUtils.createTable(connectionsource, Secao.class);
			TableUtils.createTable(connectionsource, Produto.class);
			TableUtils.createTable(connectionsource, Imagem.class);
			TableUtils.createTable(connectionsource, Loja.class);
//			TableUtils.createTable(connectionsource, Atributo.class);
//			TableUtils.createTable(connectionsource, Promocao.class);
			TableUtils.createTable(connectionsource, ItemPedido.class);
			TableUtils.createTable(connectionsource, DadosPagamento.class);
//			TableUtils.createTable(connectionsource, CEP.class);
//			TableUtils.createTable(connectionsource, FaixaPreco.class);
			
			return;
		} catch (SQLException sqlexception) {
			throw new RuntimeException(sqlexception);
		}
	}

	@Override
	public void close() {
		super.close();
		estadoDao = null;
		enderecoDAO = null;
		clienteDao = null;
		formaPagamentoDao = null;
		pedidoDao = null;
		secaoDao = null;
		produtoDao = null;
		itemDao = null;
		pagtoDao = null;
//		cepDAO = null;
//		faixaPrecoDao = null;
		imagemDao = null;
//		atributoDao = null;

	}

//	public Dao<CEP, Long> getCEPDao() throws SQLException {
//		if (cepDAO == null) {
//			cepDAO = getDao(CEP.class);
//		}
//		return cepDAO;
//	}

	public Dao<Cliente, Long> getClienteDao() throws SQLException {
		if (clienteDao == null) {
			clienteDao = getDao(Cliente.class);
		}
		return clienteDao;
	}

	public Dao<Endereco, Long> getEnderecoDao() throws SQLException {
		if (enderecoDAO == null) {
			enderecoDAO = getDao(Endereco.class);
		}
		return enderecoDAO;
	}

	public Dao<DadosPagamento, Long> getDadosPagamento() throws SQLException {
		if (pagtoDao == null) {
			pagtoDao = getDao(DadosPagamento.class);
		}
		return pagtoDao;
	}

	public Dao<Estado, Long> getEstadoDao() throws SQLException {
		if (estadoDao == null) {
			estadoDao = getDao(Estado.class);
		}
		return estadoDao;
	}

//	public Dao<FaixaPreco, Long> getFaixaPrecoDao() throws SQLException {
//		if (faixaPrecoDao == null) {
//			faixaPrecoDao = getDao(FaixaPreco.class);
//		}
//		return faixaPrecoDao;
//	}

	public Dao<FormaPagamento, Long> getFormaPagamentoDao() throws SQLException {
		if (formaPagamentoDao == null) {
			formaPagamentoDao = getDao(FormaPagamento.class);
		}
		return formaPagamentoDao;
	}

	public Dao<ItemPedido, Long> getItemDao() throws SQLException {
		if (itemDao == null) {
			itemDao = getDao(ItemPedido.class);
		}
		return itemDao;
	}

	public Dao<Pedido, Long> getPedidoDao() throws SQLException {
		if (pedidoDao == null) {
			pedidoDao = getDao(Pedido.class);
		}
		return pedidoDao;
	}

	public Dao<Produto, Long> getProdutoDao() throws SQLException {
		if (produtoDao == null) {
			produtoDao = getDao(Produto.class);
		}
		return produtoDao;
	}

	public Dao<Secao, Long> getSecaoDao() throws SQLException {
		if (secaoDao == null) {
			secaoDao = getDao(Secao.class);
		}
		return secaoDao;
	}

	@Override
	public void onCreate(final SQLiteDatabase sqlitedatabase,
			final ConnectionSource connectionsource) {
		createDatabase(connectionsource);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase sqlitedatabase,
			final ConnectionSource connectionsource, final int oldversion,
			final int newversion) {
	}

	public Dao<Imagem, Long> getImagemDao() throws SQLException {
		if (imagemDao == null) {
			imagemDao = getDao(Imagem.class);
		}
		return imagemDao;
	}

//	public Dao<Atributo, Long> getAtributoDao() throws SQLException {
//		if (atributoDao == null) {
//			atributoDao = getDao(Atributo.class);
//		}
//		return atributoDao;
//	}

}
