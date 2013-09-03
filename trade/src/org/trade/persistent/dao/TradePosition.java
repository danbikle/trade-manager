/* ===========================================================
 * TradeManager : a application to trade strategies for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2011-2011, by Simon Allen and Contributors.
 *
 * Project Info:  org.trade
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Java is a trademark or registered trademark of Oracle, Inc.
 * in the United States and other countries.]
 *
 * (C) Copyright 2011-2011, by Simon Allen and Contributors.
 *
 * Original Author:  Simon Allen;
 * Contributor(s):   -;
 *
 * Changes
 * -------
 *
 */
package org.trade.persistent.dao;

// Generated Feb 21, 2011 12:43:33 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.trade.core.dao.Aspect;
import org.trade.core.valuetype.Money;

/**
 * Trade generated by hbm2java
 * 
 * @author Simon Allen
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tradeposition")
public class TradePosition extends Aspect implements java.io.Serializable {

	/**
	 * There can only ever be one TradePosition open at a time for a Contract. A
	 * TradePosition is created when at least one TradeOrder becomes
	 * filled/partially filled.
	 * 
	 * The TradeOrder that caused the TradePosition to be created is marked as
	 * the opening TradeOrder.
	 * 
	 * A TradePosition is closed when the total open quantity i.e. buy quantity
	 * less sell quantity equals zero. At this point the net value represents
	 * the P/L for the position.
	 */

	private static final long serialVersionUID = 715993951200025530L;
	private ContractLite contract;
	private Integer openQuantity = new Integer(0);
	private Date positionOpenDate;
	private Date positionCloseDate;
	private String side;
	private BigDecimal totalCommission;
	private Integer totalBuyQuantity;
	private BigDecimal totalBuyValue;
	private Integer totalSellQuantity;
	private BigDecimal totalSellValue;
	private BigDecimal totalNetValue;
	private Date lastUpdateDate;
	private List<TradeOrder> tradeOrders = new ArrayList<TradeOrder>(0);

	public TradePosition() {
	}

	/**
	 * Constructor for TradePosition.
	 * 
	 * @param contract
	 *            Contract
	 * @param positionOpenDate
	 *            Date
	 * @param side
	 *            String
	 */
	public TradePosition(Contract contract, Date positionOpenDate, String side) {
		this.contract = new ContractLite(contract.getIdContract());
		this.positionOpenDate = positionOpenDate;
		this.side = side;
		this.lastUpdateDate = positionOpenDate;
	}

	/**
	 * Constructor for TradePosition.
	 * 
	 * @param contract
	 *            Contract
	 * @param positionOpenDate
	 *            Date
	 * @param side
	 *            String
	 */
	public TradePosition(ContractLite contract, Date positionOpenDate,
			String side) {
		this.contract = contract;
		this.positionOpenDate = positionOpenDate;
		this.side = side;
		this.lastUpdateDate = positionOpenDate;
	}

	/**
	 * Constructor for TradePosition.
	 * 
	 * @param contract
	 *            Contract
	 * @param positionOpenDate
	 *            Date
	 * @param positionCloseDate
	 *            Date
	 * @param openQuantity
	 *            Integer
	 * @param side
	 *            String
	 * @param totalBuyQuantity
	 *            Integer
	 * @param totalBuyValue
	 *            BigDecimal
	 * @param totalSellQuantity
	 *            Integer
	 * @param totalSellValue
	 *            BigDecimal
	 * @param totalNetValue
	 *            BigDecimal
	 * @param tradeOrders
	 *            List<TradeOrder>
	 */
	public TradePosition(Contract contract, Date positionOpenDate,
			Date positionCloseDate, Integer openQuantity, String side,
			BigDecimal totalCommission, Integer totalBuyQuantity,
			BigDecimal totalBuyValue, Integer totalSellQuantity,
			BigDecimal totalSellValue, BigDecimal totalNetValue,
			List<TradeOrder> tradeOrders) {
		this.contract = new ContractLite(contract.getIdContract());
		this.positionOpenDate = positionOpenDate;
		this.positionCloseDate = positionCloseDate;
		this.openQuantity = openQuantity;
		this.side = side;
		this.totalCommission = totalCommission;
		this.totalBuyQuantity = totalBuyQuantity;
		this.totalBuyValue = totalBuyValue;
		this.totalSellQuantity = totalSellQuantity;
		this.totalSellValue = totalSellValue;
		this.totalNetValue = totalNetValue;
		this.tradeOrders = tradeOrders;
		this.lastUpdateDate = positionOpenDate;
	}

	/**
	 * Method getIdTradePosition.
	 * 
	 * @return Integer
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTradePosition", unique = true, nullable = false)
	public Integer getIdTradePosition() {
		return this.id;
	}

	/**
	 * Method setIdTradePosition.
	 * 
	 * @param idTradePosition
	 *            Integer
	 */
	public void setIdTradePosition(Integer idTradePosition) {
		this.id = idTradePosition;
	}

	/**
	 * Method getContract.
	 * 
	 * @return ContractLite
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idContract", insertable = true, updatable = true, nullable = false)
	public ContractLite getContract() {
		return this.contract;
	}

	/**
	 * Method setContract.
	 * 
	 * @param contract
	 *            ContractLite
	 */
	public void setContract(ContractLite contract) {
		this.contract = contract;
	}

	/**
	 * Method getPositionOpenDate.
	 * 
	 * @return Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "positionOpenDate", nullable = false, length = 19)
	public Date getPositionOpenDate() {
		return this.positionOpenDate;
	}

	/**
	 * Method setPositionOpenDate.
	 * 
	 * @param positionOpenDate
	 *            Date
	 */
	public void setPositionOpenDate(Date positionOpenDate) {
		this.positionOpenDate = positionOpenDate;
	}

	/**
	 * Method getPositionCloseDate.
	 * 
	 * @return Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "positionCloseDate", nullable = true, length = 19)
	public Date getPositionCloseDate() {
		return this.positionCloseDate;
	}

	/**
	 * Method setPositionCloseDate.
	 * 
	 * @param positionCloseDate
	 *            Date
	 */
	public void setPositionCloseDate(Date positionCloseDate) {
		this.positionCloseDate = positionCloseDate;
	}

	/**
	 * Method getOpenQuantity.
	 * 
	 * @return Integer
	 */
	@Column(name = "openQuantity")
	public Integer getOpenQuantity() {
		return this.openQuantity;
	}

	/**
	 * Method setOpenQuantity.
	 * 
	 * @param openQuantity
	 *            Integer
	 */
	public void setOpenQuantity(Integer openQuantity) {
		this.openQuantity = openQuantity;
	}

	/**
	 * Method getSide.
	 * 
	 * @return String
	 */
	@Column(name = "side", nullable = false, length = 3)
	public String getSide() {
		return this.side;
	}

	/**
	 * Method setSide.
	 * 
	 * @param side
	 *            String
	 */
	public void setSide(String side) {
		this.side = side;
	}

	/**
	 * Method getTotalCommission.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "totalCommission", precision = 10)
	public BigDecimal getTotalCommission() {
		return this.totalCommission;
	}

	/**
	 * Method setTotalCommission.
	 * 
	 * @param totalCommission
	 *            BigDecimal
	 */
	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	/**
	 * Method getTotalBuyQuantity.
	 * 
	 * @return Integer
	 */
	@Column(name = "totalBuyQuantity")
	public Integer getTotalBuyQuantity() {
		return this.totalBuyQuantity;
	}

	/**
	 * Method setTotalBuyQuantity.
	 * 
	 * @param totalBuyQuantity
	 *            Integer
	 */
	public void setTotalBuyQuantity(Integer totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	/**
	 * Method getTotalSellQuantity.
	 * 
	 * @return Integer
	 */
	@Column(name = "totalSellQuantity")
	public Integer getTotalSellQuantity() {
		return this.totalSellQuantity;
	}

	/**
	 * Method setTotalSellQuantity.
	 * 
	 * @param totalSellQuantity
	 *            Integer
	 */
	public void setTotalSellQuantity(Integer totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
	}

	/**
	 * Method getTotalBuyValue.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "totalBuyValue", precision = 10)
	public BigDecimal getTotalBuyValue() {
		return this.totalBuyValue;
	}

	/**
	 * Method setTotalBuyValue.
	 * 
	 * @param totalBuyValue
	 *            BigDecimal
	 */
	public void setTotalBuyValue(BigDecimal totalBuyValue) {
		this.totalBuyValue = totalBuyValue;
	}

	/**
	 * Method getTotalSellValue.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "totalSellValue", precision = 10)
	public BigDecimal getTotalSellValue() {
		return this.totalSellValue;
	}

	/**
	 * Method setTotalSellValue.
	 * 
	 * @param totalSellValue
	 *            BigDecimal
	 */
	public void setTotalSellValue(BigDecimal totalSellValue) {
		this.totalSellValue = totalSellValue;
	}

	/**
	 * Method getTotalNetValue.
	 * 
	 * @return BigDecimal
	 */
	@Column(name = "totalNetValue", precision = 10)
	public BigDecimal getTotalNetValue() {
		return this.totalNetValue;
	}

	/**
	 * Method setTotalNetValue.
	 * 
	 * @param totalNetValue
	 *            BigDecimal
	 */
	public void setTotalNetValue(BigDecimal totalNetValue) {
		this.totalNetValue = totalNetValue;
	}

	/**
	 * Method getLastUpdateDate.
	 * 
	 * @return Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastUpdateDate", nullable = false, length = 19)
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	/**
	 * Method setLastUpdateDate.
	 * 
	 * @param lastUpdateDate
	 *            Date
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * Method getVersion.
	 * 
	 * @return Integer
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	/**
	 * Method setVersion.
	 * 
	 * @param version
	 *            Integer
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Method getTradeOrders.
	 * 
	 * @return List<TradeOrder>
	 */
	@OneToMany(mappedBy = "tradePosition", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH, CascadeType.REMOVE })
	public List<TradeOrder> getTradeOrders() {
		return this.tradeOrders;
	}

	/**
	 * Method setTradeOrders.
	 * 
	 * @param tradeOrders
	 *            List<TradeOrder>
	 */
	public void setTradeOrders(List<TradeOrder> tradeOrders) {
		this.tradeOrders = tradeOrders;
	}

	/**
	 * Method addTradeOrder.
	 * 
	 * @param tradeOrders
	 *            TradeOrder
	 */
	public void addTradeOrder(TradeOrder tradeOrders) {
		this.tradeOrders.add(tradeOrders);
	}

	/**
	 * Method isOpen.
	 * 
	 * @return boolean
	 */
	@Transient
	public boolean isOpen() {
		if (this.equals(this.getContract().getTradePosition()))
			return true;
		return false;
	}

	/**
	 * Method containsTradeOrder.
	 * 
	 * @param tradeOrder
	 *            TradeOrder
	 * @return boolean
	 */
	@Transient
	public boolean containsTradeOrder(TradeOrder tradeOrder) {
		for (TradeOrder item : this.getTradeOrders()) {
			if (item.getOrderKey().equals(tradeOrder.getOrderKey())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	public String toString() {
		return "TradePosition Id: " + this.getIdTradePosition() + " Version: "
				+ this.getVersion() + " positionOpenDate: "
				+ this.getPositionOpenDate() + " positionCloseDate: "
				+ this.getPositionCloseDate() + " Side: " + this.getSide()
				+ " Open Qty: " + this.getOpenQuantity() + " Total Buy qty: "
				+ this.getTotalBuyQuantity() + " Total Buy Value: "
				+ new Money(this.getTotalBuyValue()) + " Total Sell qty: "
				+ this.getTotalSellQuantity() + " Total Sell Value: "
				+ new Money(this.getTotalSellValue()) + " Total Comm: "
				+ new Money(this.getTotalCommission()) + " updateDate: "
				+ this.getLastUpdateDate();
	}
}
