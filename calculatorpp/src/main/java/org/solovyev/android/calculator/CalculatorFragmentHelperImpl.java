package org.solovyev.android.calculator;

import android.support.v4.app.Fragment;
import org.jetbrains.annotations.NotNull;

/**
 * User: serso
 * Date: 9/26/12
 * Time: 10:14 PM
 */
public class CalculatorFragmentHelperImpl implements CalculatorFragmentHelper {

    @Override
    public boolean isPane(@NotNull Fragment fragment) {
        return fragment.getActivity() instanceof CalculatorActivity;
    }
}
