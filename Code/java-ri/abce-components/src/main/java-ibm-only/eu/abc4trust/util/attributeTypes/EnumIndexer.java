//* Licensed Materials - Property of IBM                              *
//* eu.abc4trust.pabce.1.0                                            *
//* (C) Copyright IBM Corp. 2012. All Rights Reserved.                *
//* US Government Users Restricted Rights - Use, duplication or       *
//* disclosure restricted by GSA ADP Schedule Contract with IBM Corp. *
//*/**/****************************************************************

package eu.abc4trust.util.attributeTypes;

import java.math.BigInteger;

public interface EnumIndexer {
  public BigInteger getRepresentationOfIndex(int index);
  public Integer getIndexFromRepresentation(BigInteger repr);
}