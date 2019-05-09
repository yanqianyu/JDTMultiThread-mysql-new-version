package easymvp.compiler.generator.decorator;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import easymvp.compiler.generator.DelegateClassGenerator;

import static easymvp.compiler.generator.AndroidLoaderUtils.getLoader;
import static easymvp.compiler.generator.AndroidLoaderUtils.getLoaderCallbacks;
import static easymvp.compiler.generator.AndroidLoaderUtils.getLoaderManager;
import static easymvp.compiler.generator.AndroidLoaderUtils.getPresenterLoader;

/**
 * @author Saeed Masoumi (saeed@6thsolution.com)
 */
public class FragmentDecorator extends BaseDecorator {

    public FragmentDecorator(DelegateClassGenerator delegateClassGenerator) {
        super(delegateClassGenerator);
    }

    @Override
    public MethodSpec getLoaderManagerMethod(MethodSpec.Builder methodSignature) {
        return methodSignature.addStatement("return view.getLoaderManager()")
                .returns(getLoaderManager())
                .build();
    }

    @Override
    public String createContextField(String viewField) {
        return "final $T context = " + viewField + ".getActivity().getApplicationContext()";
    }

    @Override
    protected void implementInitializer(MethodSpec.Builder method) {

    }

    @Override
    protected ClassName getPresenterLoaderClass() {
        return getPresenterLoader();
    }

    @Override
    protected ClassName getLoaderCallbacksClass() {
        return getLoaderCallbacks();
    }

    @Override
    protected ClassName getLoaderClass() {
        return getLoader();
    }

}
