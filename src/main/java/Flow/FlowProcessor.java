package Flow;

/**
 * 流程驱动器接口.
 */
public interface FlowProcessor {


    FullFlow agree(String result);
    FullFlow repules(String result);
    FullFlow retreat(String result);
}
