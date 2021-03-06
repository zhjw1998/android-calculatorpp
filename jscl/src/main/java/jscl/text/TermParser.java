package jscl.text;

import jscl.math.Generic;
import jscl.math.GenericVariable;
import jscl.math.JsclInteger;
import jscl.math.function.Fraction;
import jscl.math.function.Inverse;

import javax.annotation.Nonnull;

/**
 * User: serso
 * Date: 10/27/11
 * Time: 2:45 PM
 */
class TermParser implements Parser<Generic> {

    public static final Parser<Generic> parser = new TermParser();

    private TermParser() {
    }

    public Generic parse(@Nonnull Parameters p, Generic previousSumElement) throws ParseException {
        Generic result = JsclInteger.valueOf(1);

        Generic s = (Generic) UnsignedFactor.parser.parse(p, previousSumElement);

        while (true) {
            try {
                Generic b = MultiplyFactor.parser.parse(p, null);
                result = result.multiply(s);
                s = b;
            } catch (ParseException e) {
                p.exceptionsPool.release(e);
                try {
                    Generic b = DivideFactor.parser.parse(p, null);
                    if (s.compareTo(JsclInteger.valueOf(1)) == 0)
                        s = new Inverse(GenericVariable.content(b, true)).expressionValue();
                    else
                        s = new Fraction(GenericVariable.content(s, true), GenericVariable.content(b, true)).expressionValue();
                } catch (ParseException e2) {
                    p.exceptionsPool.release(e2);
                    break;
                }
            }
        }

        result = result.multiply(s);

        return result;
    }
}
