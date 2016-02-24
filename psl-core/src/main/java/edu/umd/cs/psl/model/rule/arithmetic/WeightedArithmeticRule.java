/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2015 The Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.umd.cs.psl.model.rule.arithmetic;

import edu.umd.cs.psl.model.atom.Atom;
import edu.umd.cs.psl.model.formula.Formula;
import edu.umd.cs.psl.model.rule.WeightedRule;
import edu.umd.cs.psl.model.rule.arithmetic.coefficient.Coefficient;
import edu.umd.cs.psl.model.weight.NegativeWeight;
import edu.umd.cs.psl.model.weight.PositiveWeight;
import edu.umd.cs.psl.model.weight.Weight;

/**
 * A template for {@link WeightedGroundArithmeticRule WeightedGroundArithmeticRules}.
 * 
 * @author Stephen Bach
 */
public class WeightedArithmeticRule extends AbstractArithmeticRule implements WeightedRule {
	
	protected Weight weight;
	protected boolean squared;
	protected boolean mutable;

	public WeightedArithmeticRule(Coefficient[] coeffs, Atom[] atoms, Comparator comparator, Coefficient c,
			Formula[] selectStatements, double w, boolean squared) {
		super(coeffs, atoms, comparator, c, selectStatements);
		weight = (w >= 0.0) ? new PositiveWeight(w) : new NegativeWeight(w);
		this.squared = squared;
		mutable = true;
	}

	@Override
	public Weight getWeight() {
		return weight.duplicate();
	}

	@Override
	public void setWeight(Weight w) {
		if (!mutable)
			throw new IllegalStateException("Rule weight is not mutable.");
		
		weight = w;
	}

	@Override
	public boolean isWeightMutable() {
		return mutable;
	}

	@Override
	public void setWeightMutable(boolean mutable) {
		this.mutable = mutable;
	}

}